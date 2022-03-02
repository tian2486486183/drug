package com.harlon.drugmanagement.enums;

import com.harlon.drugmanagement.utils.enums.NameValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public enum RespSystemEnum implements NameValueEnum<Integer> {

    SUCCESS(200,"success"),
    ERROR(500,"服务器内部异常")
    ;

    private Integer code;
    private String message;

    @Override
    public String getName() {
        return message;
    }

    @Override
    public Integer getValue() {
        return code;
    }
}
