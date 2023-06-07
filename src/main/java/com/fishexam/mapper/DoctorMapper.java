package com.fishexam.mapper;

import com.fishexam.pojo.DoctorPojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: FishExam
 * @description: 医生
 * @author Zhuyu 1766722033@qq.com
 * 
 * @since 2020-05-26 08:42
 **/
@Mapper
@Repository
public interface DoctorMapper {
    List<DoctorPojo> selectDoctor();

    List<DoctorPojo> selectDoctorByName(@Param("name") String name);

    String selectNameByEmailOrPhone(@Param("phoneOrEmail") String phoneOrEmail);
}
