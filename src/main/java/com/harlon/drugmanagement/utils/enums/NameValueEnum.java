package com.harlon.drugmanagement.utils.enums;

/**
 * 带有枚举值以及枚举名称的枚举接口(可使用{@link EnumUtils}中的方法)
 *
 * @author meilin.huang
 * @version 1.0
 * @date 2019-03-22 11:04 AM
 */
public interface NameValueEnum<T> extends ValueEnum {
    /**
     * 获取枚举名称
     *
     * @return 枚举名
     */
    String getName();
}
