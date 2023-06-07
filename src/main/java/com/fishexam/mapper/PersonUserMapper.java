package com.fishexam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fishexam.pojo.PersonUserPojo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: FishExam
 * @description: 医院成员管理
 * @author Zhuyu 1766722033@qq.com
 * 
 * @since 2020-05-23 10:01
 **/
@Mapper
@Repository
public interface PersonUserMapper extends BaseMapper<PersonUserPojo> {
    List<PersonUserMapper> selectPerson();

    String selectPasswordByName(String user_username);

    String selectPasswordByNameEmailPhone(String user_username);
}
