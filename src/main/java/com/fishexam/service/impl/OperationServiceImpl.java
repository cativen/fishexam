package com.fishexam.service.impl;

import com.fishexam.mapper.OperationMapper;
import com.fishexam.pojo.OperationPojo;
import com.fishexam.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zhuyu 1766722033@qq.com
 * @program: FishExam
 * @description: MapperService impl
 * @since 2020-05-25 00:00
 **/
@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    OperationMapper operationMapper;

    @Override
    public List<OperationPojo> selectOperation() {

        return operationMapper.selectOperation();
    }

    @Override
    public int updateOperation(String name, String date, String time) {
        return operationMapper.updateOperation(name, date, time);
    }
}
