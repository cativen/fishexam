package com.fishexam.dto;

import com.fishexam.pojo.WashRegister;
import lombok.Data;

import java.util.List;


@Data
public class EmailDTO {

    private String userEmail;

    private String phone;

    private List<WashRegister> washRegisterList;
}
