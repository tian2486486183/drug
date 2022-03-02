package com.harlon.drugmanagement.exception;


import com.harlon.drugmanagement.enums.RespBaiduNewsEnum;
import com.harlon.drugmanagement.enums.RespLoginOrLogoutEnum;
import com.harlon.drugmanagement.enums.RespSystemEnum;
import com.harlon.drugmanagement.enums.RespTokenExceptionEnum;
import com.harlon.drugmanagement.exception.customize.*;
import com.harlon.drugmanagement.utils.enums.EnumUtils;
import com.harlon.drugmanagement.vo.RespBean;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.apache.el.parser.Token;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @ Author     :Harlon

 * @ Description  全局异常处理

 * @ Date       :2022/2/26
*/
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(RuntimeException.class)
    public RespBean dealException(RuntimeException e) {
        if (e instanceof TokenInfoErrorException){
            return RespBean.error(RespTokenExceptionEnum.TOKEN_ILLEGAL);
        }else if (e instanceof ExpiredJwtException){
            return RespBean.error(RespTokenExceptionEnum.TOKEN_TIMEOUT);
        }else if (e instanceof SignatureException){
            return RespBean.error(RespTokenExceptionEnum.TOKEN_SIGNATURE_ERR);
        }else if (e instanceof UserNotLoginException){
            return RespBean.error(RespLoginOrLogoutEnum.Login_Not);
        }else if (e instanceof UserByBanException){
            return RespBean.error(RespLoginOrLogoutEnum.Login_Failed_Ban);
        }else if (e instanceof GrabNewsException){
            return RespBean.error(RespBaiduNewsEnum.GRAB_ERROR);
        }else if (e instanceof PictureBase64EncodeException){
            return RespBean.error(RespBaiduNewsEnum.PICTURE_ENCODE_ERR);
        }
        e.printStackTrace();
        return RespBean.error(RespSystemEnum.ERROR);
    }
}
