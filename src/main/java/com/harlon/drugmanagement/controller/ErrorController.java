package com.harlon.drugmanagement.controller;

import com.harlon.drugmanagement.exception.customize.TokenInfoErrorException;
import com.harlon.drugmanagement.exception.customize.UserByBanException;
import com.harlon.drugmanagement.exception.customize.UserNotLoginException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @ Author     :Harlon

 * @ Description 异常处理接口

 * @ Date       :2022/2/26
*/
@Controller
public class ErrorController {

    @RequestMapping("/error/expire")
    public void reThrowExpire(HttpServletRequest request){
        throw (ExpiredJwtException)request.getAttribute("expiredJwtException");
    }

    @RequestMapping("/error/token")
    public void reThrowTokenError(HttpServletRequest request){
        throw (TokenInfoErrorException)request.getAttribute("tokenException");
    }

    @RequestMapping("/error/signature")
    public void reThrowSignature(HttpServletRequest request){
        throw (SignatureException)request.getAttribute("signatureException");
    }


    @RequestMapping("/error/userNotLogin")
    public void reThrowUserNotLogin(HttpServletRequest request){
        throw (UserNotLoginException)request.getAttribute("userNotLoginException");
    }

    @RequestMapping("/error/userByBan")
    public void reThrowUserByBan(HttpServletRequest request){
        throw  (UserByBanException)request.getAttribute("userByBanException");
    }

}
