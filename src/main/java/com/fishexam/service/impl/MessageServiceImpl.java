package com.fishexam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fishexam.mapper.MessageMapper;
import com.fishexam.pojo.MessagePojo;
import com.fishexam.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: FishExam
 * @description: MapperService impl
 * @author Zhuyu 1766722033@qq.com
 * 
 * @since 2020-05-25 00:00
 **/
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;


    @Override
    public List<MessagePojo> selectMessage() {
        List<MessagePojo> messageList = messageMapper.selectList(new QueryWrapper<>());
        return messageList;
    }

    @Override
    public int updateStatus(int status) {
        return messageMapper.updateStatus(status);
    }
}
