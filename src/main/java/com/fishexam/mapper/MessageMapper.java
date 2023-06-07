package com.fishexam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fishexam.pojo.MessagePojo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: FishExam
 * @description: Message dao 层
 * @author Zhuyu 1766722033@qq.com
 * 
 * @since 2020-05-24 23:54
 **/
@Mapper
public interface MessageMapper extends BaseMapper<MessagePojo> {
    List<MessagePojo> selectMessage();

    int updateStatus(int status);
}
