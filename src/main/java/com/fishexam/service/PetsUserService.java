package com.fishexam.service;

import com.fishexam.pojo.PetsUserPojo;
import com.github.pagehelper.PageInfo;
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
public interface PetsUserService {
    PageInfo<PetsUserPojo> selectPets(Integer page);

    List<PetsUserPojo> selectPets();

    List<PetsUserPojo> selectPetsByDate(int date);

    int insterIntoPet(String number, String name, String names,
                      String age, String status, String daterangepicker,
                      int gridRadios, int bed);
}
