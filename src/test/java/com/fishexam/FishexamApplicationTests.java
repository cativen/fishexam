package com.fishexam;

import com.fishexam.mapper.OperationPlusMapper;
import com.fishexam.pojo.*;
import com.fishexam.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SpringBootTest
class FishexamApplicationTests {

    @Autowired
    OperationPlusMapper operationPlusMapper;

    @Test
    void contextLoad() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<OperationPlusPojo> wang = operationPlusMapper.selectDateAndMsgByName("王啦啦");
        Date parse = simpleDateFormat.parse("2000-01-01");
       /* for (OperationPlusPojo operationPlusPojo : wang) {
            if(parse.before(operationPlusPojo.getOperationplus_date())){
                parse=operationPlusPojo.getOperationplus_date();
                System.out.println("parse===>"+parse);
            }
        }*/
    }
}
