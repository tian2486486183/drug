package com.harlon.drugmanagement.controller;

import com.harlon.drugmanagement.exception.customize.PictureBase64EncodeException;
import com.harlon.drugmanagement.utils.AuthService;
import com.harlon.drugmanagement.utils.GeneralBasic;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analyze")
public class BaiduPictureController {
    
    @RequestMapping("/picture")
    public String analyzePicture(@RequestBody String imgStr){
        if (!StringUtils.hasText(imgStr)){
            throw new PictureBase64EncodeException();
        }
        String auth = AuthService.getAuth();
        String basic = GeneralBasic.generalBasic(imgStr, auth);
        return basic;
    }
}
