package com.harlon.drugmanagement.config.ConfigConst;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class OSSConst {

    public static String endpoin;

    public static String accessKeyId;

    public static String accessKeySecret;

    public static String bucketName;

    @Value("${oss.endpoin}")
    public String point;
    @Value("${oss.accessKeyId}")
    public String id;
    @Value("${oss.accessKeySecret}")
    public String secret;
    @Value("${oss.bucketName}")
    public String bucket;



    @PostConstruct
    public void getApiToken(){
        endpoin = this.point;
        accessKeyId = this.id;
        accessKeySecret = this.secret;
        bucketName = this.bucket;
    }

    public static String getEndpoin(){
        return endpoin;
    }

    public static String getAccessKeyId(){
        return accessKeyId;
    }
    public static String getAccessKeySecret(){
        return accessKeySecret;
    }
    public static String getBucketName(){
        return bucketName;
    }

}
