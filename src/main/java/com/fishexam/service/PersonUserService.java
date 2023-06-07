package com.fishexam.service;

import com.fishexam.mapper.PersonUserMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: FishExam
 * @description:
 * @author Zhuyu 1766722033@qq.com
 * 
 * @since 2020-05-23 10:31
 **/
@Service
@Repository
public interface PersonUserService {
    List<PersonUserMapper> selectPerson();

    String selectPasswordByName(String user_username);

    String selectPasswordByNameEmailPhone(String user_username);

    boolean savePerson(String username,String password,String phoneOrEmail);
}
