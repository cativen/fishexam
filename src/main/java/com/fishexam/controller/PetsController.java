package com.fishexam.controller;

import com.fishexam.mapper.PeopleInfoMapper;
import com.fishexam.mapper.WashRegisterMapper;
import com.fishexam.pojo.MessagePojo;
import com.fishexam.pojo.PeopleInfo;
import com.fishexam.pojo.PetsUserPojo;
import com.fishexam.pojo.WashRegister;
import com.fishexam.service.MessageService;
import com.fishexam.service.PetsUserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @program: FishExam
 * @description: 宠物管理controller层
 * @author Zhuyu 1766722033@qq.com
 *
 * @since 2020-05-24 20:35
 **/
@Controller
public class PetsController {
    private Logger logger= LoggerFactory.getLogger(PetsController.class);
    @Autowired
    PetsUserService petsUserService;
    @Autowired
    PeopleInfoMapper peopleInfoMapper;
    @Autowired
    WashRegisterMapper washRegisterMapper;

    @RequestMapping("/date")
    public String selectDate(int date, Model model){
        System.out.println(date);
        List<PetsUserPojo> petsUserPojos = petsUserService.selectPetsByDate(date);
        model.addAttribute("pets",petsUserPojos);
        return "index";
    }

    @RequestMapping("/savePets")
    public String savePets(String number,String name,String names,String age,String status,String daterangepicker,String gridRadios,String bed){
        int gender = 1;
        int beds = 1;
        if ("2".equals(gridRadios)){
            gender=2;
        }
        if (bed!=null){
            beds=0;
        }

        int i = petsUserService.insterIntoPet(number, name, names, age, status, daterangepicker, gender, beds);
        System.out.println(number+name+names+age+status+daterangepicker+gender+beds);
        System.out.println(i);
        return "redirect:/index";
    }




    public static void main(String[] args) {
        int random = (int) (Math.random()*1000000);
        System.out.println(random);
    }


}
