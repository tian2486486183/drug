package com.harlon.drugmanagement.filter;

import com.harlon.drugmanagement.entity.SysUser;
import com.harlon.drugmanagement.exception.customize.UserByBanException;
import com.harlon.drugmanagement.exception.customize.UserNotLoginException;
import com.harlon.drugmanagement.service.SysUserService;
import com.harlon.drugmanagement.utils.JwtUtil;
import com.harlon.drugmanagement.vo.LoginUser;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Objects;

@Component
public class UserStatusCheckFilter extends OncePerRequestFilter {

    @Autowired
    private SysUserService sysUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(authentication)){
            filterChain.doFilter(request,response);
            return;
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String name = loginUser.getUsername();
        SysUser user = sysUserService.queryByUsername(name);
        if (Objects.isNull(user)){
            request.setAttribute("userNotLoginException",new UserNotLoginException());
            request.getRequestDispatcher("/error/userNotLogin").forward(request,response);
            return;
        }
        if (user.getStatus().equals("1")){
            request.setAttribute("userByBanException",new UserByBanException());
            request.getRequestDispatcher("/error/userByBan").forward(request,response);
            return;
        }
        filterChain.doFilter(request,response);
    }
}
