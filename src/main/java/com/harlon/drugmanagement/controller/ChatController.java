package com.harlon.drugmanagement.controller;

import com.harlon.drugmanagement.vo.LoginUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    //TODO 聊天目录，以后有需要在做

    @GetMapping("/admin")
    public List<LoginUser> getAllUser(){
        return null;
    }

}
