package com.harlon.drugmanagement.enums;

import com.harlon.drugmanagement.utils.enums.NameValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RespBaiduNewsEnum implements NameValueEnum {
    GRAB_ERROR(800801,"新闻爬取失败"),
    PICTURE_ENCODE_ERR(800802,"图片base64编码错误")
    ;


    public Integer code;
    public String message;

    @Override
    public String getName() {
        return message;
    }

    @Override
    public Object getValue() {
        return code;
    }
}
