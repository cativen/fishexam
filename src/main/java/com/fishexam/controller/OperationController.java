package com.fishexam.controller;

import com.fishexam.service.OperationPlusService;
import com.fishexam.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: FishExam
 * @description:
 * @author Zhuyu 1766722033@qq.com
 * 
 * @since 2020-05-26 10:19
 **/
@Controller
public class OperationController {
    @Autowired
    OperationService operationService;
    @Autowired
    OperationPlusService operationPlusService;

    @RequestMapping("/addOperation")
    public String addOperation(String number, String doctor, String time, String petsName, String status, String daterangepicker) {
        String dates = daterangepicker + " " + time;
        String times = "operation_" + time;
        System.out.println(number+doctor+time+petsName+status+daterangepicker);
        System.out.println(dates+times);
        int i = operationService.updateOperation(doctor, daterangepicker, times);
        int i1 = operationPlusService.intertOperationPlus(number, dates, doctor, status, petsName);
        return "redirect:doctorBed";
    }
}
