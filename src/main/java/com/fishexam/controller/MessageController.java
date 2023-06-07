package com.fishexam.controller;

import com.fishexam.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: FishExam
 * @description: message controller 层
 * @author Zhuyu 1766722033@qq.com
 * 
 * @since 2020-05-25 09:08
 **/
@Controller
public class MessageController {
    @Autowired
    MessageService messageService;

    @PostMapping("/updateStatus")
    @ResponseBody
    public void updateStatus(int status){
        System.out.println(status);
        messageService.updateStatus(status);
    }
}
