package com.fishexam.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @program: FishExam
 * @description: 医院用户表
 * @author Zhuyu 1766722033@qq.com
 * 
 * @since 2020-05-23 10:12
 **/
@Data
@TableName("person_user")
public class PersonUserPojo {

    @TableField("user_id")
    private int userid;

    @TableField("user_username")
    private String username;

    @TableField("user_password")
    private String password;

    @TableField("userPhone")
    private String phone;

    @TableField("user_email")
    private String email;

    @TableField("user_post")
    private String post;

    @TableField("user_purview")
    private String purview;

}
