package com.harlon.drugmanagement.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;
/**
 * @ Author     :Harlon

 * @ Description 验证码生成类

 * @ Date       :2022/2/27
*/
@Component
public class GenerateVerificationCodeUtil {
    /**
     * 数字
     */
    private static final char[] SYMBOLS_NUM = "0123456789".toCharArray();

    /**
     * 数字及大小写字母
     */
    private static final char[] SYMBOLS_CHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /**
     * 安全随机数
     */
    private static final Random RANDOM = new SecureRandom();

    /**
     * 生成数字验证码
     *
     * @param length 长度
     * @return 验证码
     */
    public static String generateNum(int length) {
        return generateVerifyCode(length, true);
    }

    /**
     * 生成数字及大小写字母验证码
     *
     * @param length 长度
     * @return 验证码
     */
    public static String generateChar(int length) {
        return generateVerifyCode(length, false);
    }

    /**
     * 生成数字及大小写字母验证码
     *
     * @param length  长度
     * @param onlyNum 是否只包含数字
     * @return 验证码
     */
    private static String generateVerifyCode(int length, boolean onlyNum) {
        char[] lib = onlyNum ? SYMBOLS_NUM : SYMBOLS_CHAR;
        char[] nonceChars = new char[length];
        for (int index = 0; index < nonceChars.length; index++) {
            nonceChars[index] = lib[RANDOM.nextInt(lib.length)];
        }
        return new String(nonceChars);
    }



}
