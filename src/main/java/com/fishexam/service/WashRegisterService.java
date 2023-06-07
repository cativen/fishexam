package com.fishexam.service;

import com.fishexam.pojo.WashRegister;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cativen
 * @since 2023-03-02
 */
public interface WashRegisterService extends IService<WashRegister> {

    List<WashRegister> queryRecentMsg();

    /**
     * 查询当日需要发送邮件的登记
     */
    List<WashRegister> querySendMail();

}
