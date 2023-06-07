package com.fishexam.mapper;

import com.fishexam.pojo.OperationPlusPojo;
import com.fishexam.pojo.OperationPojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @program: FishExam
 * @description: operation dao层
 * @author Zhuyu 1766722033@qq.com
 * 
 * @since 2020-05-26 08:05
 **/
@Mapper
@Repository
public interface OperationPlusMapper {
    List<OperationPlusPojo> selectOperationPlus();

    int intertOperationPlus(@Param("number") String number, @Param("date") String date, @Param("doctor") String doctor, @Param("msg") String msg,@Param("petsName") String petsName);

    List<OperationPlusPojo> selectDateAndMsgByName(@Param("name") String name);
}
