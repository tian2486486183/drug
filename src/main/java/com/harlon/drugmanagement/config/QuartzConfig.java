package com.harlon.drugmanagement.config;

import com.harlon.drugmanagement.quartz.GranBaiduNewsJop;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    //bj-cynosdbmysql-grp-cltmu3au.sql.tencentcdb.com:29639


    @Bean
    public JobDetail jobDetail(){
        return JobBuilder.newJob(GranBaiduNewsJop.class).withIdentity("grapBaiduNews-jobDetail").storeDurably().build();
    }

    @Bean
    public CronTrigger cronTrigger(){
        //宕机之后不干活
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 1 * * ?").withMisfireHandlingInstructionDoNothing();

        CronTrigger cronTrigger = TriggerBuilder.newTrigger().forJob(jobDetail())
                .withIdentity("grapBaiduNews-tragger")
                .withDescription("爬取百度新闻")
                .withSchedule(cronScheduleBuilder)
                .startNow()
                .build();

        return cronTrigger;
    }
}
