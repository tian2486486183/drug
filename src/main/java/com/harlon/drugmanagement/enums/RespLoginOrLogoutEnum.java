package com.harlon.drugmanagement.enums;

import com.harlon.drugmanagement.utils.enums.NameValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

 /**
  * @ Author     :Harlon

  * @ Description 登录登出功能相关枚举类

  * @ Date       :2022/2/26
 */

@AllArgsConstructor
@ToString
@Getter
public enum RespLoginOrLogoutEnum implements NameValueEnum<Integer> {

     Login_ERR(500501,"登录失败，用户名或密码错误"),
     Login_SUCCESS(500502,"登录成功"),
     Logout_SUCCESS(500503,"注销成功"),
     Login_Failed_Ban(500504,"该账户已被封禁"),
     Login_Failed_Code_Error(500505,"验证码错误"),
     Login_Not(500506,"用户未登录"),
     INFO_GET_SUCCESS(500507,"用户信息获取成功"),
     USER_BY_BAN(500508,"该用户已被封禁，请检点～")
     ;


    private final Integer code;
    private final String message;

    @Override
    public String getName() {
        return message;
    }

    @Override
    public Integer getValue() {
        return code;
    }
}
