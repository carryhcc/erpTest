package com.example.controller;

import com.example.service.WxRebootService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2022/1/17 13:50
 */
@Slf4j
@RestController
public class WxRebootController {

    @Resource
    private WxRebootService wxRebootService;

    public void moFish() {
        wxRebootService.moFish();
        log.info("摸🐟提醒执行完毕");
    }

}
