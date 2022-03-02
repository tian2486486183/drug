package com.harlon.drugmanagement.filter;

import com.harlon.drugmanagement.config.ConfigConst.RedisConst;
import com.harlon.drugmanagement.exception.customize.TokenInfoErrorException;
import com.harlon.drugmanagement.exception.customize.UserNotLoginException;
import com.harlon.drugmanagement.utils.JwtUtil;
import com.harlon.drugmanagement.utils.RedisCache;
import com.harlon.drugmanagement.vo.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationSTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取 token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)){
            filterChain.doFilter(request,response);
            return;
        }
        //解析 token
        String userid = null;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (MalformedJwtException e) {
            request.setAttribute("tokenException",new TokenInfoErrorException());
            request.getRequestDispatcher("/error/token").forward(request,response);
            return;
        }catch (ExpiredJwtException e) {
            request.setAttribute("expiredJwtException",e);
            request.getRequestDispatcher("/error/expire").forward(request,response);
            return;
        } catch (AccessDeniedException | SignatureException e) {
            request.setAttribute("signatureException", e);
            request.getRequestDispatcher("/error/signature").forward(request, response);
            return;
        } catch (Exception e){}
        //从 redis 中获取信息
        String redisKey = RedisConst.LOGIN_USER_ID+userid;
        LoginUser user = redisCache.getCacheObject(redisKey);
        if (Objects.isNull(user)){
            request.setAttribute("userNotLoginException",new UserNotLoginException());
            request.getRequestDispatcher("/error/userNotLogin").forward(request,response);
            return;
        }
        //存入 SecurityContext
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user,null,null
        );

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }
}
