package com.fishexam.service.impl;

import com.fishexam.mapper.DoctorMapper;
import com.fishexam.mapper.OperationMapper;
import com.fishexam.pojo.DoctorPojo;
import com.fishexam.pojo.OperationPojo;
import com.fishexam.service.DoctorService;
import com.fishexam.service.OperationService;
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
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorMapper doctorMapper;

    @Override
    public List<DoctorPojo> selectDoctor() {
        return doctorMapper.selectDoctor();
    }

    @Override
    public List<DoctorPojo> selectDoctorByName(String name) {
        return doctorMapper.selectDoctorByName(name);
    }

    @Override
    public String selectNameByEmailOrPhone(String phoneOrEmail) {
        return doctorMapper.selectNameByEmailOrPhone(phoneOrEmail);
    }
}
