package com.harlon.drugmanagement.service;

import com.harlon.drugmanagement.entity.SysUser;
import com.harlon.drugmanagement.vo.RespBean;

import javax.servlet.http.HttpServletRequest;

/**
 * @ Author     :Harlon

 * @ Description 该接口用于处理登录登出相关业务

 * @ Date       :2022/2/26
*/
public interface LoginOrLogoutService {


    RespBean login(String userName, String password, String code, HttpServletRequest request);

    RespBean logout();
}
