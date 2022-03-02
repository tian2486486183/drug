package com.harlon.drugmanagement.controller;

import com.harlon.drugmanagement.entity.SysUser;
import com.harlon.drugmanagement.vo.ChatMsg;
import com.harlon.drugmanagement.vo.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Collection;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/ws/chat")
    public void handlerMsg(Authentication authentication, ChatMsg msg){
        LoginUser loginUser = (LoginUser) authentication.getAuthorities();
        SysUser user = loginUser.getUser();
        msg.setFrom(user.getUserName());
        msg.setFromNickName(user.getNickName());
        msg.setDate(LocalDateTime.now());
        simpMessagingTemplate.convertAndSendToUser(msg.getTo(),"/queue/chat",msg);
    }
}
