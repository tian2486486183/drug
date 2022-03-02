package com.harlon.drugmanagement.controller;

import com.harlon.drugmanagement.config.ConfigConst.RedisConst;
import com.harlon.drugmanagement.entity.BaiduNews;
import com.harlon.drugmanagement.utils.BaiduNewsJsoupUtil;
import com.harlon.drugmanagement.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/news")
public class BaiduNewsController {

    @Autowired
    private BaiduNewsJsoupUtil baiduNewsJsoupUtil;
    @Autowired
    private RedisCache redisCache;

    @RequestMapping("/{count}")
    public List<BaiduNews> getNews(@PathVariable("count") String count){
        int num = Integer.parseInt(count);
        return baiduNewsJsoupUtil.getFiveNews(num);
    }

    @RequestMapping("/today")
    public List<BaiduNews> getNewsToday(){
        List news = redisCache.getCacheObject(RedisConst.NEWS_KEY);
        return news;
    }

    //TODO 完成公告与建站时间倒计时。

}
