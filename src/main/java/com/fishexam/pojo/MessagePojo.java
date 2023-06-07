package com.fishexam.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: FishExam
 * @description: message的pojo层
 * @author Zhuyu 1766722033@qq.com
 * 
 * @since 2020-05-24 23:44
 **/
@Data
@TableName(value = "message")
public class MessagePojo {
    private int messageId;
    private String messageName;
    private String messagePhoto;
    private String messageDaytime;
    private String messageMsg;
    private int messageDoctorid;
    private int messageStatus;
    private Date messageDatetime;

}
