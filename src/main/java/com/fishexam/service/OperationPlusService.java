package com.fishexam.service;

import com.fishexam.pojo.OperationPlusPojo;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zhuyu 1766722033@qq.com
 * @program: FishExam
 * @description: PetsUser的service层
 * @since 2020-05-23 10:45
 **/
@Service
@Repository
public interface OperationPlusService {
    List<OperationPlusPojo> selectOperationPlus();

    int intertOperationPlus(String number, String date, String doctor, String msg, String petsName);

    List<OperationPlusPojo> selectDateAndMsgByName(String name);
}
