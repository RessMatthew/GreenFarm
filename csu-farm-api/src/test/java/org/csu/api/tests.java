package org.csu.api;

import com.google.common.collect.Maps;
import org.csu.farm.bean.enums.SmsType;
import org.csu.farm.service.SmsLogService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class tests{

    @Autowired
    private SmsLogService smsLogService;

    @Test
    public void sms(){
        smsLogService.sendSms(SmsType.VALID,"111","18390858705",Maps.newHashMap());
    }



}
