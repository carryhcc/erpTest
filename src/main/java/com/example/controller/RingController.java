package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * 随机生成神器💍
 *
 * @author : cchu
 * Date: 2022/2/9 10:28
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class RingController {


    @PostMapping("/ring")
    public String ring() {
        log.info("ring");
        return "ring";
    }
}
