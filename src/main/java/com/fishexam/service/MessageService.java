package com.fishexam.service;

import com.fishexam.pojo.MessagePojo;
import com.fishexam.pojo.PetsUserPojo;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: FishExam
 * @description: PetsUser的service层
 * @author Zhuyu 1766722033@qq.com
 * 
 * @since 2020-05-23 10:45
 **/
@Service
@Repository
public interface MessageService {
    List<MessagePojo> selectMessage();

    int updateStatus(int status);

}
