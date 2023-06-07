package com.fishexam.service;

import com.fishexam.pojo.DoctorPojo;
import com.fishexam.pojo.OperationPojo;
import org.apache.ibatis.annotations.Param;
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
public interface DoctorService {
    List<DoctorPojo> selectDoctor();

    List<DoctorPojo> selectDoctorByName(String name);

    String selectNameByEmailOrPhone(String phoneOrEmail);
}
