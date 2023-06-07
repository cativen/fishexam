package com.fishexam.mapper;

import com.fishexam.pojo.OperationPojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: FishExam
 * @description: operation dao层
 * @author Zhuyu 1766722033@qq.com
 * 
 * @since 2020-05-26 08:05
 **/
@Mapper
@Repository
public interface OperationMapper {
    List<OperationPojo> selectOperation();

    int updateOperation(@Param("name") String name,@Param("date") String date,@Param("time") String time);
}
