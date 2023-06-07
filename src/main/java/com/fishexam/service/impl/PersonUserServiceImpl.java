package com.fishexam.service.impl;

import com.fishexam.mapper.PersonUserMapper;
import com.fishexam.pojo.PersonUserPojo;
import com.fishexam.service.PersonUserService;
import com.fishexam.util.PasswordLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: FishExam
 * @description: PersonUserService实现类
 * @author Zhuyu 1766722033@qq.com
 * 
 * @since 2020-05-23 10:43
 **/
@Service
public class PersonUserServiceImpl implements PersonUserService {
    @Autowired
    PersonUserMapper personUserMapper;

    @Autowired
    PasswordLock passwordLocks;

    @Override
    public List<PersonUserMapper> selectPerson() {
        return personUserMapper.selectPerson();
    }

    @Override
    public String selectPasswordByName(String user_username) {
        return personUserMapper.selectPasswordByName(user_username);
    }

    @Override
    public String selectPasswordByNameEmailPhone(String user_username) {
        return personUserMapper.selectPasswordByNameEmailPhone(user_username);
    }

    @Override
    public boolean savePerson(String username, String password, String phoneOrEmail) {
        PersonUserPojo personUserPojo = new PersonUserPojo();
        personUserPojo.setEmail(phoneOrEmail);
        personUserPojo.setUsername(username);
        personUserPojo.setPassword(passwordLocks.passwordLocks(password));
        personUserPojo.setPost("新人");
        personUserPojo.setPurview("3");
        return personUserMapper.insert(personUserPojo)==1;
    }
}
