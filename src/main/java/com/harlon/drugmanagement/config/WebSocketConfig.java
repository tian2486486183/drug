package com.harlon.drugmanagement.config;

import com.harlon.drugmanagement.config.ConfigConst.RedisConst;
import com.harlon.drugmanagement.entity.SysUser;
import com.harlon.drugmanagement.exception.customize.TokenInfoErrorException;
import com.harlon.drugmanagement.service.SysUserService;
import com.harlon.drugmanagement.utils.JwtUtil;
import com.harlon.drugmanagement.utils.RedisCache;
import com.harlon.drugmanagement.vo.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private UserDetailsService userDetailsServiceImpl;
    /**
     * @Description 配置用于前端连接
     * @param registry
     * * @return void
     * @author harlon
     * @date 2022/2/28 11:57 AM
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /**
         * 1.将ws/ep路径注册为stomp的端点，用户连接了这个端点就可以进行websocket通讯，支持socketJS
         * 2.setAllowedOrigins("*")：允许跨域
         * 3.withSockJS():支持socketJS访问
         */
        registry.addEndpoint("/ws/ep").setAllowedOrigins("*").withSockJS();
    }

    /**
     * @Description 输入通道参数配置
     * @param registration
     * * @return void
     * @author harlon
     * @date 2022/2/28 12:00 PM
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {

                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                //判断是否为连接
                if (StompCommand.CONNECT.equals(accessor.getCommand())){
                    String token = accessor.getFirstNativeHeader("token");
                    if (StringUtils.hasText(token)){
                        try {
                            Claims claims = JwtUtil.parseJWT(token);
                            String userid = claims.getSubject();
                            SysUser sysUser = sysUserService.queryById(Long.parseLong(userid));
                            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(sysUser.getUserName());
                            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                    userDetails,null,null
                            );
                            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        }  catch (Exception e){
                            throw new TokenInfoErrorException();
                        }
                    }
                }
                return message;
            }
        });
    }
    /**
     * @Description 配置消息代理
     * @param registry
     * * @return void
     * @author harlon
     * @date 2022/2/28 11:59 AM
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //配置代理域，可以配置多个，配置代理目的地前缀为/queue,可以在配置域上向客户端推送消息
        registry.enableSimpleBroker("/queue");
    }
}
