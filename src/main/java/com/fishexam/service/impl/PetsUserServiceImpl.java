package com.fishexam.service.impl;

import com.fishexam.mapper.PersonUserMapper;
import com.fishexam.mapper.PetsUserMapper;
import com.fishexam.pojo.PetsUserPojo;
import com.fishexam.service.PersonUserService;
import com.fishexam.service.PetsUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @program: FishExam
 * @description: PetsUserService的实现类
 * @author Zhuyu 1766722033@qq.com
 * 
 * @since 2020-05-23 10:45
 **/
@Service
public class PetsUserServiceImpl implements PetsUserService {

    @Autowired
    PetsUserMapper petsUserMapper;


    @Override
    public List<PetsUserPojo> selectPets() {
        List<PetsUserPojo> petsUserPojos = petsUserMapper.selectPets();
        return petsUserMapper.selectPets();
    }

    @Override
    public PageInfo<PetsUserPojo> selectPets(Integer page) {
        PageHelper.startPage(page, 8);
        List<PetsUserPojo> petsUserPojos = petsUserMapper.selectPets();
        PageInfo<PetsUserPojo> info = new PageInfo<>(petsUserPojos);
        return info;
    }

    @Override
    public List<PetsUserPojo> selectPetsByDate(int date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        Date dates = new Date();
        long time = dates.getTime();
        long l = time - 24 * 60 * 60 * 1000 * date;
        String format = df.format(l);
        return petsUserMapper.selectPetsByDate(format);
    }

    @Override
    public int insterIntoPet(String number, String name, String names, String age, String status, String daterangepicker, int gridRadios, int bed) {
        return petsUserMapper.insterIntoPet(number, name, names, age, status, daterangepicker, gridRadios, bed);
    }
}
