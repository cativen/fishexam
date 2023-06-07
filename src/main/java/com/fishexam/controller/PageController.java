package com.fishexam.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
    import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fishexam.mapper.PeopleInfoMapper;
import com.fishexam.mapper.WashRegisterMapper;
import com.fishexam.pojo.*;
import com.fishexam.service.MessageService;
import com.fishexam.service.OperationService;
import com.fishexam.service.PetsUserService;
import com.fishexam.service.WashRegisterService;
import com.github.pagehelper.PageException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Transactional
public class PageController {
    @Autowired
    PetsUserService petsUserService;


    @Autowired
    MessageService messageService;

    @Autowired
    OperationService operationService;

    @Autowired
    PeopleInfoMapper peopleInfoMapper;

    @Autowired
    WashRegisterMapper washRegisterMapper;

    @Autowired
    private WashRegisterService washRegisterService;

    private Logger logger= LoggerFactory.getLogger(PageController.class);


    @RequestMapping({"/login", "/login.html", "/"})
    public String login() {
        return "login/login";
    }

    //主页
    @RequestMapping("/index")
    public String goToHome(Model model, Integer page) {
        if (page == null) {
            page = 1;
        }
        IPage<WashRegister> washRegisterIPage=new Page(page,10);
        IPage<WashRegister> iPages=washRegisterMapper.selectPageInfo(washRegisterIPage);
        long pages = iPages.getPages();
        List<WashRegister> records = iPages.getRecords();
        long total = iPages.getTotal();
        model.addAttribute("page", page);
        model.addAttribute("totalPage", pages);
        model.addAttribute("washRegisters",records);
        model.addAttribute("total", total);
        //查询通知秘书消息
        List<WashRegister> washRegisterList=washRegisterService.queryRecentMsg();

        model.addAttribute("messageStatusSize",washRegisterList.size());
        model.addAttribute("msgs",washRegisterList);
        return "index";
    }



    @RequestMapping("/indexs")
    public String goToHomes(Model model, Integer page) {
        if (page == null) {
            page = 1;
        }
        PageInfo<PetsUserPojo> petsUserPojoPageInfo = petsUserService.selectPets(page);
        model.addAttribute("info", petsUserPojoPageInfo);
        System.out.println(petsUserPojoPageInfo);
        return "indexs";
    }





    //手术排班信息
    @RequestMapping("/doctorBed")
    public String doctorBed(Model model) {
        List<MessagePojo> messagePojos = messageService.selectMessage();
        List<OperationPojo> operation = operationService.selectOperation();

        //获取未读消息 messageStatusSize的个数
        int messageStatusSize = 0;
        for (MessagePojo messagePojo : messagePojos) {
            int messageStatus = messagePojo.getMessageStatus();
            if (messageStatus == 0) {
                messageStatusSize++;
            }
        }
        String messageStatusSizes = messageStatusSize + "条未读消息";
        model.addAttribute("operation", operation);
        model.addAttribute("messageStatusSize", messageStatusSizes);
        model.addAttribute("msgs", messagePojos);
        return "home/doctorBed";
    }

    //添加新问诊宠物
    @RequestMapping("/addPets")
    public String addPets(Model model) {
        List<MessagePojo> messagePojos = messageService.selectMessage();

        //获取未读消息 messageStatusSize的个数
        int messageStatusSize = 0;
        for (MessagePojo messagePojo : messagePojos) {
            int messageStatus = messagePojo.getMessageStatus();
            if (messageStatus == 0) {
                messageStatusSize++;
            }
        }
        String messageStatusSizes = messageStatusSize + "条未读消息";
        model.addAttribute("messageStatusSize", messageStatusSizes);
        model.addAttribute("msgs", messagePojos);
        return "addPets";
    }

    @RequestMapping("/toOperation")
    public String toOperation(Model model) {
        List<MessagePojo> messagePojos = messageService.selectMessage();
        //获取未读消息 messageStatusSize的个数
        int messageStatusSize = 0;
        for (MessagePojo messagePojo : messagePojos) {
            int messageStatus = messagePojo.getMessageStatus();
            if (messageStatus == 0) {
                messageStatusSize++;
            }
        }
        String messageStatusSizes = messageStatusSize + "条未读消息";
        model.addAttribute("messageStatusSize", messageStatusSizes);
        model.addAttribute("msgs", messagePojos);
        return "home/addOperation";
    }



    @RequestMapping("/customerList")
    public String customerList(Model model, Integer page) {
        if (page == null) {
            page = 1;
        }
        IPage<WashRegister> washRegisterIPage=new Page(page,10);
        IPage<WashRegister> iPages=washRegisterMapper.selectDistinctPage(washRegisterIPage);
        long pages = iPages.getPages();
        List<WashRegister> records = iPages.getRecords();
        long total = iPages.getTotal();
        model.addAttribute("page", page);
        model.addAttribute("totalPage", pages);
        model.addAttribute("washRegisters",records);
        model.addAttribute("total", total);
        //查询通知秘书消息
        List<WashRegister> washRegisterList=washRegisterService.queryRecentMsg();

        model.addAttribute("messageStatusSize",washRegisterList.size());
        model.addAttribute("msgs",washRegisterList);
        return "customer";
    }



    @RequestMapping("/addPeople")
    public String addPeople() {
        return "addpeople";
    }



    @RequestMapping("/personChat")
    public String chat() {
        return "home/chat";
    }

    @RequestMapping("/webSocket")
    public String webSocket() {
        return "WebSocket";
    }
}
