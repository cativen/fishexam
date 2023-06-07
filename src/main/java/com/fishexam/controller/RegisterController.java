package com.fishexam.controller;

import com.fishexam.service.PersonUserService;
import com.fishexam.util.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * @author Zhuyu 1766722033@qq.com
 * @program: FishExam
 * @description: 注册controller层
 * @since 2020-05-23 10:53
 **/
@Controller
//JSR303校验
@Validated
public class RegisterController {
    @Autowired
    PersonUserService personUserService;

    @Autowired
    EmailUtils emailUtils;


    @RequestMapping("/register")
    @ResponseBody
    public String registerUser(String username, String password,String phoneOrEmail) {
        if (username != null) {
            System.out.println("username====>" + username + "\n" + "password====>" + password + "\n" + "phoneOrEmail====>" + phoneOrEmail);
            if (phoneOrEmail.length() == 11) {
                System.out.println("手机号");
            } else {
                System.out.println("邮箱");
                if (personUserService.savePerson(username, password, phoneOrEmail)) return "success";
            }
            return "fail";
        }

        return "fail";
    }

    public String code(String code) {

        return "";
    }

    @Async
    public String verification() {

        return "success";
    }

    @PostMapping("/captchaCode")
    @ResponseBody
    public String captcha(String phone) {
        System.out.println(phone);
        int flag = new Random().nextInt(999999);
        if (flag < 100000) {
            flag += 100000;
        }
        String Verification = String.valueOf(flag);
        System.out.println("Verification = " + Verification);
        //支持异步
        System.out.println(emailUtils.registerEmail(phone, Verification));

//        String msm = AliyunMsg.aliThree(phone, Verification);
//        String msm = ShotMessages.msm(phone, Verification);
        return Verification;
    }
}
