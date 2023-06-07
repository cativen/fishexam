package com.fishexam.controller;

import com.fishexam.dto.EmailDTO;
import com.fishexam.service.PeopleInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTaskController {

    private Logger logger= LoggerFactory.getLogger(SaticScheduleTaskController.class);
    @Autowired
    private PeopleInfoService peopleInfoService;
    //3.添加定时任务
    //每隔1分钟执行一次：0 */1 * * * ?
    //每日9点提醒通知
   // @Scheduled(cron = "0 0 9 * * ?")
    //@Scheduled(cron = "0 */2 * * * ?")
    //@Scheduled(cron = "0 */1 * * * ?")
    @Scheduled(cron = "0 0 9 * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        List<EmailDTO> emailAndContent = peopleInfoService.findEmailAndContent();
        int i = peopleInfoService.sendEmail(emailAndContent);

        //发送短信通知
        peopleInfoService.sendMessage(emailAndContent);
        logger.info(i==1?"通知成功":"通知失败");
        logger.info("执行静态定时任务时间:{} " ,LocalDateTime.now());
    }
}
