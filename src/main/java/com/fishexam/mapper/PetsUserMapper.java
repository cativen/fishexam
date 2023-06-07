package com.fishexam.mapper;

import com.fishexam.pojo.PetsUserPojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: FishExam
 * @description: 宠物管理mapper层
 * @author Zhuyu 1766722033@qq.com
 * 
 * @since 2020-05-23 10:25
 **/
@Mapper
@Repository
public interface PetsUserMapper {
    List<PetsUserPojo> selectPets();

    List<PetsUserPojo> selectPetsByDate(String date);

    int insterIntoPet(@Param("number") String number,@Param("name") String name,@Param("names") String names,
                      @Param("age") String age,@Param("status") String status,@Param("daterangepicker") String daterangepicker,
                      @Param("gender") int gridRadios,@Param("bed") int bed);
}
