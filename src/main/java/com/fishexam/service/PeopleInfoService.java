package com.fishexam.service;

import com.fishexam.dto.EmailDTO;
import com.fishexam.pojo.PeopleInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cativen
 * @since 2023-03-01
 */
public interface PeopleInfoService extends IService<PeopleInfo> {

    /**
     *
     * 发送邮件
     * @param
     * @return
     */
    int sendEmail(List<EmailDTO> emailAndContent );

   /* *//**
     * 查询要发送的邮件地址和内容
     *//*
    List<EmailDTO> findEmailAndContent(List<EmailDTO> emailAndContent );*/

    void sendMessage(List<EmailDTO> emailAndContent);

    List<EmailDTO> findEmailAndContent();
}
