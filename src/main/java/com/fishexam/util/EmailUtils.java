package com.fishexam.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author Zhuyu 1766722033@qq.com
 * @program: FishExam
 * @description: 邮件发送功能
 * @since 2020-05-22 12:08
 **/
@Component
@EnableAsync//开启异步注解
public class EmailUtils {

    @Autowired
    JavaMailSenderImpl mailSender;


    @Async
    public String remindEmail(String email, String name, String date, String msg) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            //主题
            helper.setSubject("来自煮鱼宠物医院的一封邮件");
            //正文
            helper.setText("【煮鱼宠物医院】 您好" + name + "医生,今天" + date + "有您的手术,是" + msg + "的一台手术,不要迟到了呀 -----由【煮鱼宠物医院】手动提醒", true);
            //附件
//            helper.addAttachment("test.jpg",new File("C:\\Users\\Administrator\\Desktop\\1.jpg"));
//            helper.addAttachment("test2.jpg",new File("C:\\Users\\Administrator\\Desktop\\1.jpg"));
            //收件人
            helper.setTo(email);
            helper.setFrom("wzy990506@qq.com");
            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
            return "fail";
        }

        return "success";
    }

    @Async
    public String registerEmail(String email, String msg) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            //主题
            helper.setSubject("来自煮鱼宠物医院的验证码");
            //正文
            helper.setText("【煮鱼宠物医院】 短信验证码：【" + msg + "】 -----由【煮鱼宠物医院】提醒", true);
            //附件
//            helper.addAttachment("test.jpg",new File("C:\\Users\\Administrator\\Desktop\\1.jpg"));
//            helper.addAttachment("test2.jpg",new File("C:\\Users\\Administrator\\Desktop\\1.jpg"));
            //收件人
            helper.setTo(email);
            helper.setFrom("1766722033@qq.com");
            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
            return "fail";
        }

        return "success";
    }
}
