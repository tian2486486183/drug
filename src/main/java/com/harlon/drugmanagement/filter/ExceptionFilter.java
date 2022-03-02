package com.harlon.drugmanagement.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @ Author     :Harlon
 
 * @ Description 异常过滤器
 
 * @ Date       :2022/2/26
*/
@Component
public class ExceptionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            filterChain.doFilter(request,response);
        }catch (Exception e){
            request.setAttribute("filter.exception",e);
            request.getRequestDispatcher("/error/ex").forward(request,response);
        }
    }
}
