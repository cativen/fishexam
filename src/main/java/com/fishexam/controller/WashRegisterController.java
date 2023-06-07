package com.fishexam.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fishexam.dto.EmailDTO;
import com.fishexam.mapper.PeopleInfoMapper;
import com.fishexam.mapper.WashRegisterMapper;
import com.fishexam.pojo.MessagePojo;
import com.fishexam.pojo.PeopleInfo;
import com.fishexam.pojo.PetsUserPojo;
import com.fishexam.pojo.WashRegister;
import com.fishexam.service.PeopleInfoService;
import com.fishexam.service.WashRegisterService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cativen
 * @since 2023-03-02
 */
@Controller
@Transactional
public class WashRegisterController {
    @Autowired
    private WashRegisterMapper washRegisterMapper;
    @Autowired
    private WashRegisterService washRegisterService;
    @Autowired
    private PeopleInfoService peopleInfoService;

    private Logger logger= LoggerFactory.getLogger(WashRegisterController.class);

    @RequestMapping("/deleteWashRegister")
    public String deleteWashRegister(int pid) {
        WashRegister washRegister = washRegisterMapper.selectById(pid);
        if (washRegister!=null){
            washRegister.setDelFlag(1);
            int i = washRegisterMapper.updateById(washRegister);
            logger.info(i==1?"删除成功":"删除失败");
        }

        return "redirect:/washRegister";
    }

    @RequestMapping("/editWashRegister")
    public String editWashRegister(int pid, Model model) {
        WashRegister washRegister = washRegisterMapper.selectById(pid);
        model.addAttribute("info", washRegister);
        //查询通知秘书消息
        List<WashRegister> washRegisterList=washRegisterService.queryRecentMsg();

        model.addAttribute("messageStatusSize",washRegisterList.size());
        model.addAttribute("msgs",washRegisterList);
        return "prepare/editregister";
    }

    @RequestMapping("/washRegister")
    public String washRegister(Model model, Integer page) {
        if (page == null) {
            page = 1;
        }
        IPage<WashRegister> washRegisterIPage=new Page(page,10);
        IPage<WashRegister> iPages=washRegisterMapper.selectPageInfo(washRegisterIPage);
        long pages = iPages.getPages();
        List<WashRegister> records = iPages.getRecords();
        long total = iPages.getTotal();
        model.addAttribute("page", page);
        model.addAttribute("totalPage", pages);
        model.addAttribute("washRegisters",records);
        model.addAttribute("total", total);
        //查询通知秘书消息
        List<WashRegister> washRegisterList=washRegisterService.queryRecentMsg();

        model.addAttribute("messageStatusSize",washRegisterList.size());
        model.addAttribute("msgs",washRegisterList);
        return "washregister";
    }

    @RequestMapping("/modifyRegister")
    public String modifyRegister(int registerId,String name,String petName,String phone,String washDate,int advanceDay,int type,String serviceDate) {
        WashRegister washRegister = washRegisterMapper.selectById(registerId);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        washRegister.setName(name);
        washRegister.setPetName(petName);
        washRegister.setPhone(phone);
        washRegister.setAdvanceDay(advanceDay);
        washRegister.setType(type);
        if (StringUtils.isNotEmpty(washDate)){
            try {
                Date parseWashDate = simpleDateFormat.parse(washDate);
                washRegister.setWashDate(parseWashDate);
                Date parseCurrentDate = simpleDateFormat.parse(serviceDate);
                washRegister.setServiceDate(parseCurrentDate);
                int day= (int) ((parseWashDate.getTime()-parseCurrentDate.getTime())/(24*60*60*1000));
                washRegister.setTimeSpan(day);
            } catch (ParseException e) {
                logger.info("洗护日期解析失败");
                System.out.println("解析失败");
                e.printStackTrace();
            }
        }
        washRegisterMapper.updateById(washRegister);
        return "redirect:/washRegister";
    }

    @RequestMapping("/addRegister")
    public String addRegister() {
        return "addregister";
    }

    @RequestMapping("/saveWashRegister")
    public String saveWashRegister(String name,String petName,String phone,String washDate,int advanceDay,int type,String serviceDate){
        if (name==null){
            throw new RuntimeException("姓名不能为空!");
        }



        if (petName==null){
            throw new RuntimeException("宠物昵称不能为空!");
        }

        if (phone==null){
            throw new RuntimeException("电话号码不能为空!");
        }

        if (phone.length()!=11){
            throw new RuntimeException(phone.length()+"位电话号码长度不正确!");
        }

        if (washDate==null){
            throw new RuntimeException("下次洗护日期不能为空!");
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        WashRegister washRegister=new WashRegister();
        washRegister.setName(name);
        washRegister.setPetName(petName);
        washRegister.setPhone(phone);
        washRegister.setAdvanceDay(advanceDay);
        washRegister.setCreateDate(new Date());
        washRegister.setType(type);
        washRegister.setWashRegisterNumber("YYNO"+format.format(new Date())+String.valueOf((int) (Math.random()*1000+1000)));
        if (StringUtils.isNotEmpty(washDate)){
            try {
                Date parseWashDate = simpleDateFormat.parse(washDate);
                washRegister.setWashDate(parseWashDate);
                Date parseCurrentDate = simpleDateFormat.parse(serviceDate);
                washRegister.setServiceDate(parseCurrentDate);
                int day= (int) ((parseWashDate.getTime()-parseCurrentDate.getTime())/(24*60*60*1000));
                washRegister.setTimeSpan(day);
            } catch (ParseException e) {
                logger.info("洗护日期解析失败");
                System.out.println("解析失败");
                e.printStackTrace();
            }
        }
        washRegister.setMsgNotify(0);
        washRegisterMapper.insert(washRegister);
        return "redirect:/washRegister";
    }

   @RequestMapping("/test")
    public void test() {
       EmailDTO emailDTO=new EmailDTO();
       emailDTO.setUserEmail("327029415@qq.com");
       WashRegister washRegister = new WashRegister();
       washRegister.setWashDate(new Date());
       washRegister.setName("谢雯");
       washRegister.setPetName("麦兜");
      /* emailDTO.setWashRegister(washRegister);
       int i = peopleInfoService.sendEmail(emailDTO);*/
   }
}

