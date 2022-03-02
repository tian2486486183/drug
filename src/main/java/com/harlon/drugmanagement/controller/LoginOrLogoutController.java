package com.harlon.drugmanagement.controller;


import com.harlon.drugmanagement.entity.SysUser;
import com.harlon.drugmanagement.enums.RespEmailOrNewsEnum;
import com.harlon.drugmanagement.enums.RespLoginOrLogoutEnum;
import com.harlon.drugmanagement.service.LoginOrLogoutService;
import com.harlon.drugmanagement.service.SysUserService;
import com.harlon.drugmanagement.validation.IphoneValidationUtil;
import com.harlon.drugmanagement.vo.LoginParams;
import com.harlon.drugmanagement.vo.RespBean;
import com.harlon.drugmanagement.vo.UpdateInfoUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Objects;

/**
 * @ Author     :Harlon

 * @ Description 登录登出相关的接口

 * @ Date       :2022/2/26
*/

@RestController
@RequestMapping("/user")
public class LoginOrLogoutController {

    @Autowired
    private LoginOrLogoutService loginOrLogoutService;

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/updateInfo")
    public RespBean updateInfo(@RequestBody UpdateInfoUser user,Principal principal){
        if (Objects.isNull(user)){
            return RespBean.error(RespEmailOrNewsEnum.INFO_REGEX_ERR);
        }
        if (!user.getEmail().endsWith("@qq.com")){
            return RespBean.error(RespEmailOrNewsEnum.EMAIL_REGEX_ERR);
        }
        if (!IphoneValidationUtil.isPhone(user.getPhonenumber())){
            return RespBean.error(RespEmailOrNewsEnum.PHONE_NUM_ERR);
        }
        SysUser sysUser = sysUserService.queryByUsername(principal.getName());
        BeanUtils.copyProperties(user,sysUser);
        sysUserService.update(sysUser);
        return RespBean.success(RespEmailOrNewsEnum.INFO_UPDATE_SUCCESS);
    }

    /**
     * @Description 用户登录
     * @param params
     * @param request
     * * @return com.harlon.drugmanagement.vo.RespBean
     * @author harlon
     * @date 2022/2/28 9:03 AM
     */
    @PostMapping("/login")
    public RespBean login(@RequestBody LoginParams params, HttpServletRequest request){
        RespBean login = loginOrLogoutService.login(params.getUserName(),params.getPassword(),params.getCode(),request);
        return login;
    }

    /**
     * @Description 注销登录
     * @param
     * * @return com.harlon.drugmanagement.vo.RespBean
     * @author harlon
     * @date 2022/2/28 9:03 AM
     */
    @GetMapping("/logout")
    public RespBean logout(){
        RespBean logout = loginOrLogoutService.logout();
        return logout;
    }

    /**
     * @Description 获取当前用户信息
     * @param principal
     * * @return com.harlon.drugmanagement.vo.RespBean
     * @author harlon
     * @date 2022/2/28 8:59 AM
     */
    @GetMapping("/getInfo")
    public RespBean getUserInfo(Principal principal){
        SysUser user = sysUserService.queryByUsername(principal.getName());
        if (Objects.isNull(user)){
            return RespBean.error(RespLoginOrLogoutEnum.Login_Not);
        }
        user.setPassword(null);
        user.setUserType(null);
        user.setDelFlag(null);
        user.setStatus(null);
        return RespBean.success(RespLoginOrLogoutEnum.INFO_GET_SUCCESS,user);
    }
}
