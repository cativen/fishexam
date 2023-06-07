package com.fishexam.service.impl;

import com.fishexam.pojo.WashRegister;
import com.fishexam.mapper.WashRegisterMapper;
import com.fishexam.service.WashRegisterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cativen
 * @since 2023-03-02
 */
@Service
public class WashRegisterServiceImpl extends ServiceImpl<WashRegisterMapper, WashRegister> implements WashRegisterService {

    public static long ONE_DAY=24*60*60*1000L;
    @Override
    public List<WashRegister> queryRecentMsg() {
        //按提醒时间由近到远排序
        return  baseMapper.queryRecentMsg();
    }

    @Override
    public List<WashRegister> querySendMail() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String nowFormat = simpleDateFormat.format(date); //今日时间

        List<WashRegister> needSendMailList=new ArrayList<>();
        //查询需要发送邮件的登记
        List<WashRegister> washRegisters = baseMapper.queryRecentMsg();
        if (!CollectionUtils.isEmpty(washRegisters)){
            for (WashRegister washRegister : washRegisters) {
                Date washDate = washRegister.getWashDate();
                if (washDate!=null){
                    int advanceDay = washRegister.getAdvanceDay();
                    long washDateTime = washDate.getTime();
                    long advanceTime=advanceDay*ONE_DAY;
                    long calTime=washDateTime-advanceTime;
                    Date finalDate = new Date(calTime);
                    String washFormat = simpleDateFormat.format(finalDate); //洗护日期
                    if (washFormat.equals(nowFormat)){
                        needSendMailList.add(washRegister);
                    }
                }
            }
        }
        return needSendMailList;
    }
}
