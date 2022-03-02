package com.harlon.drugmanagement.utils;

import com.harlon.drugmanagement.config.ConfigConst.RedisConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class GeneratePrimaryKeyByRedis {

    @Autowired
    private RedisTemplate redisTemplate;


    public Long getPrimaryKey(){
        RedisAtomicLong redisAtomicLong = new RedisAtomicLong(RedisConst.PRIMARY_KEY, redisTemplate.getConnectionFactory());
        long result = redisAtomicLong.get();
        if (result == 0){
            redisAtomicLong.set(Long.parseLong(RedisConst.PRIMARY_KEY_FIRST_VALUE));
            long andIncrement = redisAtomicLong.getAndIncrement();
            return andIncrement;
        }else {

            RedisAtomicLong redisIncr = new RedisAtomicLong(RedisConst.PRIMARY_KEY,redisTemplate.getConnectionFactory());
            Long increment = redisIncr.getAndIncrement();
            return increment;
        }
    }

}
