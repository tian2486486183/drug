package com.harlon.drugmanagement.quartz;

import com.harlon.drugmanagement.config.ConfigConst.RedisConst;
import com.harlon.drugmanagement.utils.BaiduNewsJsoupUtil;
import com.harlon.drugmanagement.utils.RedisCache;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;
import java.util.Objects;

@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class GranBaiduNewsJop extends QuartzJobBean {

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private BaiduNewsJsoupUtil baiduNewsJsoupUtil;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        List news = baiduNewsJsoupUtil.getFiveNews(5);
        if (!Objects.isNull(news)){
            redisCache.setCacheObject(RedisConst.NEWS_KEY,news);
        }
    }
}
