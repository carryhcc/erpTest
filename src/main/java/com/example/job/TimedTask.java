package com.example.job;

import com.example.service.WxRebootService;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * Created by IntelliJ IDEA.
 * 定时任务
 *
 * @author : cchu
 * Date: 2022/1/17 13:47
 */
@Component
@Log4j2
public class TimedTask {

    @Resource
    private WxRebootService wxRebootService;

    /**
     * 每天执行一次 摸🐟
     */
    @Scheduled(cron = "0 0 10 * * ?")
    private void medicineStatistics() {
        log.info("摸鱼计时器任务开始执行");
        wxRebootService.moFish();
        log.info("摸鱼计时器任务执行结束");
    }
}
