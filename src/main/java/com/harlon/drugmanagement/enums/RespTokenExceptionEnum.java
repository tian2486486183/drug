package com.harlon.drugmanagement.enums;

import com.harlon.drugmanagement.utils.enums.NameValueEnum;
import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * @ Author     :Harlon

 * @ Description  Token 相关枚举

 * @ Date       :2022/2/27
*/
@AllArgsConstructor
@ToString
public enum RespTokenExceptionEnum implements NameValueEnum<Integer> {


    TOKEN_ILLEGAL(600601,"Token非法"),
    TOKEN_TIMEOUT(600602,"Token超时，请重新登录"),
    TOKEN_SIGNATURE_ERR(600603,"签名错误，请重新登录");

    private Integer code;
    private String message;


    @Override
    public String getName() {
        return message;
    }

    @Override
    public Object getValue() {
        return code;
    }
}
