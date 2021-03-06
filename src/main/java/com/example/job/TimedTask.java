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
    private void moFish() {
        log.info("摸鱼计时器任务开始执行");
        wxRebootService.moFish();
        log.info("摸鱼计时器任务执行结束");
    }

    /**
     * 每周三11，50提醒抬饭
     */
    @Scheduled(cron = "0 50 11 ? * WED")
    private void eat() {
        log.info("订饭任务开始执行");
        String msg="现在是星期三中午11:50,准备开始抬饭！！！";
        wxRebootService.runText(msg);
        log.info("订饭任务执行结束");
    }

    /**
     * 每周五17:00提醒写周报
     */
    @Scheduled(cron = "0 0 17 ? * FRI")
    private void weekly() {
        log.info("写周报任务开始执行");
        String msg="现在是星期五下午17:00,准备写周报！！！";
        wxRebootService.runText(msg);
        log.info("写周报任务执行结束");
    }

    /**
     * 每天18:00提醒写logTime
     */
    @Scheduled(cron = "0 0 18 * * ?")
    private void logTime() {
        log.info("提醒写logTime开始执行");
        wxRebootService.runLogTimeMsg();
        log.info("提醒写logTime执行完毕");
    }
}
