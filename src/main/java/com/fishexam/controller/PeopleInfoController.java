package com.fishexam.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fishexam.mapper.PeopleInfoMapper;
import com.fishexam.pojo.MessagePojo;
import com.fishexam.pojo.PeopleInfo;
import com.fishexam.pojo.PetsUserPojo;
import com.fishexam.pojo.WashRegister;
import com.fishexam.service.WashRegisterService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cativen
 * @since 2023-03-01
 */
@Controller
@Transactional
public class PeopleInfoController {

    private Logger logger= LoggerFactory.getLogger(PeopleInfoController.class);
    @Autowired
    private PeopleInfoMapper peopleInfoMapper;

    @Autowired
    private WashRegisterService washRegisterService;

    //编辑人员信息
    @RequestMapping("/editPeople")
    public String editPeople(@RequestParam(name = "pid")Integer pid, Model model) {
        if (pid == null) {
            pid = 1;
        }
        model.addAttribute("page", pid);
        System.out.println(pid);
        PeopleInfo peopleInfo = peopleInfoMapper.selectById(pid);
        model.addAttribute("info", peopleInfo);
        //查询通知秘书消息
        List<WashRegister> washRegisterList=washRegisterService.queryRecentMsg();

        model.addAttribute("messageStatusSize",washRegisterList.size());
        model.addAttribute("msgs",washRegisterList);
        return "prepare/editpeople";
    }

    @RequestMapping("/modifyPeople")
    public String modifyPeople(int peopleId,String peopleName,String email,String peoplePhone,int peopleAge) {
        PeopleInfo peopleInfo = peopleInfoMapper.selectById(peopleId);
        if (peopleInfo!=null){
            peopleInfo.setPeopleName(peopleName);
            peopleInfo.setEmail(email);
            peopleInfo.setPeoplePhone(peoplePhone);
            peopleInfo.setPeopleAge(peopleAge);
            peopleInfoMapper.updateById(peopleInfo);
        }
        return "redirect:/toPeople";
    }

    @RequestMapping("/deletePeople")
    public String deletePeople(int pid) {
        PeopleInfo peopleInfo = peopleInfoMapper.selectById(pid);
        if (peopleInfo!=null){
            peopleInfo.setDelFlag(1);
            int i = peopleInfoMapper.updateById(peopleInfo);
            logger.info(i==1?"删除成功":"删除失败");
        }
        return "redirect:/toPeople";
    }

    @RequestMapping("/savePeople")
    public String savePeople(String peopleName,String email,String peoplePhone,int peopleAge){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        if (peopleName==null){
            throw new RuntimeException("昵称不能为空!");
        }

        if (email==null){
            throw new RuntimeException("邮箱不能为空!");
        }

        if (peoplePhone==null){
            throw new RuntimeException("电话号码不能为空!");
        }

        if (peoplePhone.length()!=11){
            throw new RuntimeException(peoplePhone.length()+"位电话号码长度不正确!");
        }
        PeopleInfo peopleInfo=new PeopleInfo();
        peopleInfo.setPeopleName(peopleName);
        peopleInfo.setEmail(email);
        peopleInfo.setPeoplePhone(peoplePhone);
        peopleInfo.setPeopleAge(peopleAge);
        peopleInfo.setCreateDate(new Date());
        peopleInfo.setPeopleNumber("RYNO"+simpleDateFormat.format(new Date())+String.valueOf((int) (Math.random()*1000+1000)));
        peopleInfoMapper.insert(peopleInfo);
        return "redirect:/toPeople";
    }

    //人员信息表
    @RequestMapping("/toPeople")
    public String toPeople(Model model, Integer page) {
        if (page == null) {
            page = 1;
        }

        //查询通知秘书消息
        List<WashRegister> washRegisterList=washRegisterService.queryRecentMsg();

        //查询人员信息
        IPage<PeopleInfo> washRegisterIPage=new Page(page,10);
        IPage<PeopleInfo> iPages=peopleInfoMapper.selectPage(washRegisterIPage,new LambdaQueryWrapper<PeopleInfo>().eq(PeopleInfo::getDelFlag,0));
        model.addAttribute("peoples",iPages.getRecords());
        long pages = iPages.getPages();
        long total = iPages.getTotal();
        model.addAttribute("page", page);
        model.addAttribute("totalPage", pages);
        model.addAttribute("total", total);

        model.addAttribute("messageStatusSize",washRegisterList.size());
        model.addAttribute("msgs",washRegisterList);
        return "people";
    }



}

