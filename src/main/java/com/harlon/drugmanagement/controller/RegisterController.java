package com.harlon.drugmanagement.controller;


import com.harlon.drugmanagement.config.ConfigConst.EmailConst;
import com.harlon.drugmanagement.config.ConfigConst.RedisConst;
import com.harlon.drugmanagement.entity.SysUser;
import com.harlon.drugmanagement.enums.RespEmailOrNewsEnum;
import com.harlon.drugmanagement.service.SysUserService;
import com.harlon.drugmanagement.utils.GeneratePrimaryKeyByRedis;
import com.harlon.drugmanagement.utils.GenerateVerificationCodeUtil;
import com.harlon.drugmanagement.utils.RedisCache;
import com.harlon.drugmanagement.utils.SendSmsUtil;
import com.harlon.drugmanagement.utils.mail.MailService;
import com.harlon.drugmanagement.validation.IphoneValidationUtil;
import com.harlon.drugmanagement.vo.EmailCode;
import com.harlon.drugmanagement.vo.PhoneCode;
import com.harlon.drugmanagement.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @ Author     :Harlon

 * @ Description 用户注册相关

 * @ Date       :2022/2/27
*/
@RequestMapping("/user/register")
@RestController
public class RegisterController {

    //全局变量
    private String publicCode;

    @Autowired
    private MailService mailService;
    @Autowired
    TemplateEngine templateEngine;
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SendSmsUtil sendSmsUtil;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private GeneratePrimaryKeyByRedis generatePrimaryKeyByRedis;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registerByEmail")
    public RespBean registerByEmail(@RequestBody EmailCode emailCode){
        String email = emailCode.getEmail();
        String code = emailCode.getCode();
        if (code.length()!=6){
            return RespBean.error(RespEmailOrNewsEnum.NUM_REGEX_ERR);
        }
        //取出验证码进行比对
        String cacheObject = redisCache.getCacheObject(RedisConst.EMAIL_CODE + email);
        if (!cacheObject.equals(code)){
            return RespBean.error(RespEmailOrNewsEnum.CODE_ERR);
        }
        //获取主键
        Long primaryKey = generatePrimaryKeyByRedis.getPrimaryKey();
        //注册
        SysUser user = new SysUser();
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));
        user.setNickName(RedisConst.NEW_USERNAME+email);
        user.setUserName(email);
        user.setPassword(passwordEncoder.encode("123456"));
        user.setId(primaryKey);
        user.setStatus("0");
        user.setUserType("1");
        sysUserService.insert(user);
        HashMap<String,String> map = new HashMap<>();
        map.put("data","用户默认密码为123456");
        return RespBean.success(RespEmailOrNewsEnum.REGISTER_SUCCESS,map);
    }

    @GetMapping("/registerByPhone")
    public RespBean registerByPhone(@RequestBody PhoneCode phoneCode){
        String phone = phoneCode.getPhone();
        String code = phoneCode.getCode();
        if (code.length()!=6){
            return RespBean.error(RespEmailOrNewsEnum.NUM_REGEX_ERR);
        }
        //取出验证码进行比对
        String cacheObject = redisCache.getCacheObject(RedisConst.PHONE_CODE + phone);
        if (!cacheObject.equals(code)){
            return RespBean.error(RespEmailOrNewsEnum.CODE_ERR);
        }
        //获取主键
        Long primaryKey = generatePrimaryKeyByRedis.getPrimaryKey();
        //注册
        SysUser user = new SysUser();
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));
        user.setNickName(RedisConst.NEW_USERNAME+phone);
        user.setUserName(phone);
        user.setPassword(passwordEncoder.encode("123456"));
        user.setId(primaryKey);
        user.setStatus("0");
        user.setUserType("1");
        sysUserService.insert(user);
        HashMap<String,String> map = new HashMap<>();
        map.put("data","用户默认密码为123456");
        return RespBean.success(RespEmailOrNewsEnum.REGISTER_SUCCESS,map);
    }

    @GetMapping("/sendEmailCode/{email}")
    public RespBean sendEmailCode(@PathVariable("email") String mailTo){
        //检测邮箱格式是否规范
        if (!mailTo.endsWith("@qq.com"))
            return RespBean.error(RespEmailOrNewsEnum.EMAIL_REGEX_ERR);
        //检验是否频繁发送
        String object = redisCache.getCacheObject(RedisConst.EMAIL_CODE + mailTo);
        if (object != null){
            return RespBean.error(RespEmailOrNewsEnum.FREQUENT_SEND);
        }
        // 发件人要跟yml配置文件里填写的邮箱一致
        String mailFrom = EmailConst.getMailFrom();
        // 收件人

        // 抄送
        String cc = "";
        Context context = new Context();
        String code = GenerateVerificationCodeUtil.generateNum(6);


        context.setVariable("email",mailTo);
        context.setVariable("code", code);
        context.setVariable("min", "5");
        String content = templateEngine.process("emailTemplate.html", context);
        String result = mailService.sendHtmlMailThymeLeaf(mailFrom, "[药品管理系统]", mailTo, cc, "系统发送验证码", content);
        if ("success".equals(result)) {
            //存入redis
            redisCache.setCacheObject(RedisConst.EMAIL_CODE+mailTo,code,5, TimeUnit.MINUTES);
            return RespBean.success(RespEmailOrNewsEnum.EMAIL_SEND_SUCCESS);
        } else {
            return RespBean.error(RespEmailOrNewsEnum.EMAIL_SEND_FAILED);
        }
        
    }
    @GetMapping("/sendPhoneCode/{phoneNum}")
    public RespBean sendNewsCode(@PathVariable("phoneNum") String phoneNum){
        //检验手机号格式是否规范
        boolean phone = IphoneValidationUtil.isPhone(phoneNum);
        if (phone == false)
            return RespBean.error(RespEmailOrNewsEnum.PHONE_NUM_ERR);
        //检验是否频繁发送
        String object = redisCache.getCacheObject(RedisConst.PHONE_CODE + phoneNum);
        if (object != null){
            return RespBean.error(RespEmailOrNewsEnum.FREQUENT_SEND);
        }

        //生成验证码
        String code = GenerateVerificationCodeUtil.generateNum(6);
        //与手机号协同存入redis
        redisCache.setCacheObject(RedisConst.PHONE_CODE+phoneNum,code,5,TimeUnit.MINUTES);
        boolean result = sendSmsUtil.sendNews(phoneNum, code, "5");
        if (result == true){
            //与手机号协同存入redis
            redisCache.setCacheObject(RedisConst.PHONE_CODE+phoneNum,code,5,TimeUnit.MINUTES);
            return RespBean.success(RespEmailOrNewsEnum.PHONE_SEND_SUCCESS);
        }
        else
            return RespBean.error(RespEmailOrNewsEnum.PHONE_SEND_FAILED);
    }




}
