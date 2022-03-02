package com.harlon.drugmanagement.enums;

import com.harlon.drugmanagement.utils.enums.NameValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @ Author     :Harlon

 * @ Description 邮件相关响应枚举

 * @ Date       :2022/2/27
*/
@AllArgsConstructor
@ToString
@Getter
public enum RespEmailOrNewsEnum implements NameValueEnum<Integer> {

    EMAIL_SEND_FAILED(700701,"邮件发送失败"),
    EMAIL_SEND_SUCCESS(700702,"邮件发送成功"),
    PHONE_SEND_SUCCESS(700703,"验证码发送成功"),
    PHONE_SEND_FAILED(700704,"验证码发送失败"),
    FREQUENT_SEND(700705,"请不要频繁发送"),
    CODE_ERR(700706,"验证码错误，请稍后重试"),
    PHONE_NUM_ERR(700707,"手机号格式错误"),
    EMAIL_REGEX_ERR(700708,"邮箱格式错误"),
    NUM_REGEX_ERR(700709,"验证码格式错误"),
    REGISTER_SUCCESS(700710,"用户注册成功"),
    INFO_REGEX_ERR(700711,"用户信息为空"),
    INFO_UPDATE_SUCCESS(700712,"信息更新成功")
    ;


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
