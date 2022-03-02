package com.harlon.drugmanagement.config.security;


import com.harlon.drugmanagement.filter.JwtAuthenticationSTokenFilter;
import com.harlon.drugmanagement.filter.UserStatusCheckFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @ Author     :Harlon

 * @ Description 用于实现 SpringSecurity 的相关配置

 * @ Date       :2022/2/26
*/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationSTokenFilter jwtAuthenticationSTokenFilter;
    @Autowired
    private UserStatusCheckFilter userStatusCheckFilter;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * @Description 静态资源配置
     * @param web
     * * @return void
     * @author harlon
     * @date 2022/2/26 1:40 PM
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/**",
                "/swagger-resources/**",
                "/swagger/**",
                "/webjars/**",
                "/swagger-ui.html"
                ).antMatchers(
                        // knife4j 相关
                "/doc.html",
                    "/v2/api-docs-ext/**",
                "/captcha",
                "/ws/**"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf().disable()
                //不通过 Session 获取 SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //对于登录接口允许匿名访问
                .antMatchers("/user/login").anonymous()
                .antMatchers("doc.html").permitAll()
                .antMatchers("/user/register/sendEmailCode/**").permitAll()
                .antMatchers("/user/register/sendPhoneCode/**").permitAll()
                .antMatchers("/user/register/registerByEmail/**").permitAll()
                .antMatchers("/user/register/registerByPhone/**").permitAll()
                .antMatchers("/user/register/**").permitAll()
                //除上面外的所有请求都需要鉴权认证
                .anyRequest().authenticated();

        //添加 token 认证过滤器
        http.addFilterBefore(jwtAuthenticationSTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //添加异常处理器
//        http.addFilterBefore(exceptionFilter,JwtAuthenticationSTokenFilter.class);
        //添加账号状态检测
        http.addFilterAfter(userStatusCheckFilter,JwtAuthenticationSTokenFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
