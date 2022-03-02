package com.harlon.drugmanagement.service.impl;

import com.harlon.drugmanagement.config.ConfigConst.RedisConst;
import com.harlon.drugmanagement.entity.SysUser;
import com.harlon.drugmanagement.enums.RespLoginOrLogoutEnum;
import com.harlon.drugmanagement.service.LoginOrLogoutService;
import com.harlon.drugmanagement.utils.JwtUtil;
import com.harlon.drugmanagement.utils.RedisCache;
import com.harlon.drugmanagement.vo.LoginUser;
import com.harlon.drugmanagement.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


@Service
public class LoginOrLogoutServiceImpl implements LoginOrLogoutService {


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;


    @Override
    public RespBean login(String userName, String password, String code, HttpServletRequest request) {
        //验证码验证
        String captcha = (String) request.getSession().getAttribute("captcha");
        if (captcha == null || !captcha.equalsIgnoreCase(code)){
            return RespBean.error(RespLoginOrLogoutEnum.Login_Failed_Code_Error);
        }
        //AuthenticationManager 进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
          userName,password
        );
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        //如果认证不通过给出提示
        if (Objects.isNull(authentication)){
            return RespBean.error(RespLoginOrLogoutEnum.Login_ERR);
        }
        //如果认证通过使用 userid
        LoginUser principal = (LoginUser) authentication.getPrincipal();
        SysUser user = principal.getUser();
        if (user.getStatus().equals("1")){
            return RespBean.error(RespLoginOrLogoutEnum.Login_Failed_Ban);
        }
        String id = user.getId().toString();
        //生成jwt
        String jwt = JwtUtil.createJWT(id);
        HashMap<String,String> map = new HashMap<>();
        map.put("token",jwt);
        //存入 redis
        redisCache.setCacheObject(RedisConst.LOGIN_USER_ID+id,principal,1,TimeUnit.HOURS);
        //返回 token
        return RespBean.success(RespLoginOrLogoutEnum.Login_SUCCESS,map);
    }

    @Override
    public RespBean logout() {
        //从SecurityContext 中获取 userid
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser principal = (LoginUser) authentication.getPrincipal();
        Long id = principal.getUser().getId();
        //删除 redis 中的值
        redisCache.deleteObject(RedisConst.LOGIN_USER_ID+id);
        return RespBean.success(RespLoginOrLogoutEnum.Logout_SUCCESS);
    }
}
