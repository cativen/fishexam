package com.fishexam.controller;

import com.fishexam.pojo.DoctorPojo;
import com.fishexam.pojo.MessagePojo;
import com.fishexam.pojo.OperationPlusPojo;
import com.fishexam.service.DoctorService;
import com.fishexam.service.MessageService;
import com.fishexam.service.OperationPlusService;
import com.fishexam.util.EmailUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Zhuyu 1766722033@qq.com
 * @program: FishExam
 * @description: 医生
 * @since 2020-05-26 16:30
 **/
@Controller
public class DoctorController {
    private static final Logger log = LoggerFactory.getLogger(DoctorController.class);

    @Autowired
    DoctorService doctorService;

    @Autowired
    MessageService messageService;

    @Autowired
    OperationPlusService operationPlusService;

    @Autowired
    EmailUtils emailUtils;

    @RequestMapping("/toDoctorInfo")
    public String toDoctorInfo(String name, Model model) {
        List<MessagePojo> messagePojos = messageService.selectMessage();
        //根据名字获取一个医生信息
        List<DoctorPojo> doctor = doctorService.selectDoctorByName(name);
        //获取未读消息 messageStatusSize的个数
        int messageStatusSize = 0;
        for (MessagePojo messagePojo : messagePojos) {
            int messageStatus = messagePojo.getMessageStatus();
            if (messageStatus == 0) {
                messageStatusSize++;
            }
        }
        String messageStatusSizes = messageStatusSize + "条未读消息";
        model.addAttribute("doctor", doctor);
        model.addAttribute("messageStatusSize", messageStatusSizes);
        model.addAttribute("msgs", messagePojos);

        return "home/doctorInfo";
    }

    @RequestMapping("/toDoctors")
    public String toDoctors(Model model) {
        List<OperationPlusPojo> operationPlusPojos = operationPlusService.selectOperationPlus();
        List<MessagePojo> messagePojos = messageService.selectMessage();
        //根据名字获取一个医生信息
        List<DoctorPojo> doctor = doctorService.selectDoctor();
        //获取未读消息 messageStatusSize的个数
        int messageStatusSize = 0;
        for (MessagePojo messagePojo : messagePojos) {
            int messageStatus = messagePojo.getMessageStatus();
            if (messageStatus == 0) {
                messageStatusSize++;
            }
        }
        String messageStatusSizes = messageStatusSize + "条未读消息";
        model.addAttribute("operationPlusPojos", operationPlusPojos);
        model.addAttribute("doctor", doctor);
        model.addAttribute("messageStatusSize", messageStatusSizes);
        model.addAttribute("msgs", messagePojos);
        return "home/doctors";
    }

    @PostMapping("/sendEmailAndPhone")
    @ResponseBody
    public String sendEmailAndPhone(String phoneOrEmail) throws ParseException {
        String name = doctorService.selectNameByEmailOrPhone(phoneOrEmail);
        List<OperationPlusPojo> list = operationPlusService.selectDateAndMsgByName(name);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = simpleDateFormat.parse("2000-01-01");
        String msg = null;
        for (OperationPlusPojo operationPlusPojo : list) {
            if (parse.before(operationPlusPojo.getOperationplusDate())) {
                parse = operationPlusPojo.getOperationplusDate();
                msg = operationPlusPojo.getOperationplusMsg();
            }
        }
        String format = simpleDateFormat.format(parse);
        System.out.println(phoneOrEmail);
        emailUtils.remindEmail(phoneOrEmail, name, format, msg);
        return "success";
    }

    @RequestMapping("/toAddDoctor")
    public String toAddDoctor() {

        return "home/addDoctor";
    }

    @RequestMapping("/addDoctor")
    public String addDoctor(String number, String doctor, String post, String email, String phone, int age, int gender, String level, String msg) {
        System.out.println(number + doctor + post + email + phone + age + level + gender + msg);
        return "redirect:/toDoctors";
    }
}
