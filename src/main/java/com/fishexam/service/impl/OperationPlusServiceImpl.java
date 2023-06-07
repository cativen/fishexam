package com.fishexam.service.impl;

import com.fishexam.mapper.OperationPlusMapper;
import com.fishexam.pojo.OperationPlusPojo;
import com.fishexam.service.OperationPlusService;
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
public class OperationPlusServiceImpl implements OperationPlusService {

    @Autowired
    OperationPlusMapper operationPlusMapper;

    @Override
    public List<OperationPlusPojo> selectOperationPlus() {
        return operationPlusMapper.selectOperationPlus();
    }

    @Override
    public int intertOperationPlus(String number, String date, String doctor, String msg,String petsName) {
        return operationPlusMapper.intertOperationPlus(number,date,doctor,msg,petsName);
    }

    @Override
    public List<OperationPlusPojo> selectDateAndMsgByName(String name) {
        return operationPlusMapper.selectDateAndMsgByName(name);
    }
}
