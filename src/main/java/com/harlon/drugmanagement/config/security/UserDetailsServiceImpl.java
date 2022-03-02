package com.harlon.drugmanagement.config.security;

import com.harlon.drugmanagement.dao.SysUserDao;
import com.harlon.drugmanagement.entity.SysUser;
import com.harlon.drugmanagement.vo.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @ Author     :Harlon

 * @ Description 为了覆盖登录时 SpringSecurity 从内存查询用户信息，所以重写该类

 * @ Date       :2022/2/26
*/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser user = userDao.queryUserByUsername(username);

        if (Objects.isNull(user)){
            throw new RuntimeException("用户名或者密码错误");
        }
        //TODO 根据用户查询权限信息 添加到 LoginUser中

        return new LoginUser(user);
    }
}
