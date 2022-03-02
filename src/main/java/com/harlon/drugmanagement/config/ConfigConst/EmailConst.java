package com.harlon.drugmanagement.config.ConfigConst;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class EmailConst {

    private static String mailFrom;
    @Value("${spring.mail.username}")
    public String mail;

    @PostConstruct
    public void getApiToken(){
        mailFrom = this.mail;
    }


    public static String getMailFrom(){
        return mailFrom;
    }

}
