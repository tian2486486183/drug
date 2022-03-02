package com.harlon.drugmanagement;

import com.harlon.drugmanagement.config.ConfigConst.EmailConst;
import com.harlon.drugmanagement.config.ConfigConst.RedisConst;
import com.harlon.drugmanagement.dao.SysUserDao;
import com.harlon.drugmanagement.entity.SysUser;
import com.harlon.drugmanagement.enums.RespLoginOrLogoutEnum;
import com.harlon.drugmanagement.utils.*;
import com.harlon.drugmanagement.utils.mail.MailService;
import com.harlon.drugmanagement.vo.RespBean;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class DrugManagementApplicationTests {




    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Test
    void contextLoads() {

        Date date = new Date(System.currentTimeMillis());
        System.out.println(date);
    }

    @Test
    public void testQRCode() throws IOException {

    }

    @Test
    void testJIn(){
        for(int i = 31;i >= 0; i--)

            System.out.print(23 >>> i & 1);
        System.out.println();
    }

    @Test
    void testJsoup() throws IOException {
        String url = "http://news.baidu.com";
        Document document = Jsoup.parse(new URL(url), 30000);

        Element bigDiv = document.getElementById("pane-news");
        Elements lilist = bigDiv.getElementsByTag("li");

        for (Element element : lilist ){
            String title = element.getElementsByTag("a").eq(0).text();
            System.out.println("title:"+title);
            String attr = element.getElementsByTag("a").eq(0).attr("href");
            System.out.println(attr);
        }

    }

    @Test
    void testRedis(){
        RedisAtomicLong redisAtomicLong = new RedisAtomicLong(RedisConst.PRIMARY_KEY, redisTemplate.getConnectionFactory());
        long result = redisAtomicLong.get();
        if (result == 0){
            redisAtomicLong.set(Long.parseLong(RedisConst.PRIMARY_KEY_FIRST_VALUE));
            long andIncrement = redisAtomicLong.getAndIncrement();
            System.out.println(andIncrement);
        }else {

            RedisAtomicLong redisIncr = new RedisAtomicLong(RedisConst.PRIMARY_KEY,redisTemplate.getConnectionFactory());
            Long increment = redisIncr.getAndIncrement();

            System.out.println(increment);
        }
    }

    @Test
    void testFirstMybatisDemo(){


    }

//    @Test
//    void testRedisDemo(){
//        String name = (String) redisTemplate.opsForValue().getAndDelete("name");
//        System.out.println(name);
//    }
    @Test
    void testPasswordEncoding(){
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
    }

    @Test
    void testRespEnum(){
        HashMap map = new HashMap();
        map.put("token","sdasdasd");
        RespBean.success(RespLoginOrLogoutEnum.Login_SUCCESS,map);
    }
    @Autowired
    TemplateEngine templateEngine;
    @Autowired
    private MailService mailService;

    @Test
    void testSendEmail(){
        // 发件人要跟yml配置文件里填写的邮箱一致
        String mailFrom = EmailConst.getMailFrom();
        String mailTo = "1315077391@qq.com";
        System.out.println("发件人邮箱:"+mailFrom);
        System.out.println("收件人邮箱:"+mailTo);
        // 收件人

        // 抄送
        String cc = "";

        Context context = new Context();
        String code = GenerateVerificationCodeUtil.generateNum(6);
        System.out.println("验证码为："+code);
//        redisService.put("verificationCode",code,1);
        context.setVariable("verificationCode", code);
        context.setVariable("min", "5");
        String content = templateEngine.process("littleRaccoon.html", context);
        String result = mailService.sendHtmlMailThymeLeaf(mailFrom, "[药品管理系统]", mailTo, cc, "系统发送验证码", content);
        if ("success".equals(result)) {
            System.out.println("发送成功");
//            return RespBean.success(RespEmailOrNewsEnum.EMAIL_SEND_SUCCESS);
        } else {
            System.out.println("发送失败");
//            return RespBean.error(RespEmailOrNewsEnum.EMAIL_SEND_FAILED);
        }
    }

}
