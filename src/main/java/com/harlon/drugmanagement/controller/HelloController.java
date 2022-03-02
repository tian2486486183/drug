package com.harlon.drugmanagement.controller;


import com.harlon.drugmanagement.vo.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author     :Harlon

 * @ Description 服务器状态测试接口

 * @ Date       :2022/2/26
*/

@RestController
@RequestMapping("/user")
public class HelloController {

    @GetMapping("/hello")
    public RespBean hello(){
        return RespBean.success();
    }
}
