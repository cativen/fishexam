package com.fishexam.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @program: FishExam
 * @description: 宠物成员
 * @author Zhuyu 1766722033@qq.com
 * 
 * @since 2020-05-23 10:21
 **/
@Data
public class PetsUserPojo {
    private int petsid;
    private String number;
    private String name;
    private int gender;
    private String status;
    private Date date;
    private int bed;
    private String age;
    private String names;
}
