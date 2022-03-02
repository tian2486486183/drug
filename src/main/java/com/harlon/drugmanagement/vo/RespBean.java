package com.harlon.drugmanagement.vo;

import com.harlon.drugmanagement.enums.RespSystemEnum;
import com.harlon.drugmanagement.utils.enums.NameValueEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;


/**
 * @ Author     :Harlon

 * @ Description 公共返回对象

 * @ Date       :2022/2/25
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespBean {

    private Integer code;
    private String message;
    private Object obj;


    /**
     * @Description 成功返回的结果
     * @param
     * * @return com.harlon.drugmanagement.vo.RespBean
     * @author harlon
     * @date 2022/2/25 11:09 PM
     */
    public static RespBean success(){
        return new RespBean(RespSystemEnum.SUCCESS.getCode(), RespSystemEnum.SUCCESS.getMessage(),null);
    }

    public static RespBean success(Object obj){
        return new RespBean(RespSystemEnum.SUCCESS.getCode(), RespSystemEnum.SUCCESS.getMessage(),obj);
    }

    public static RespBean success(NameValueEnum nameValueEnum, Object obj){
        return new RespBean((Integer) nameValueEnum.getValue(), nameValueEnum.getName(), obj);
    }
    /**
     * @Description 失败返回的结果
     * @param nameValueEnum
     * * @return com.harlon.drugmanagement.vo.RespBean
     * @author harlon
     * @date 2022/2/25 11:09 PM
     */
    public static RespBean error(NameValueEnum nameValueEnum){
        return new RespBean((Integer) nameValueEnum.getValue(),nameValueEnum.getName(),null);
    }

    public static RespBean error(NameValueEnum nameValueEnum,Object obj){
        return new RespBean((Integer) nameValueEnum.getValue(),nameValueEnum.getName(),obj);
    }

}
