package com.example.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.model.User;
import com.example.service.UserService;
import com.example.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2021/12/22 17:23
 */
@Slf4j
@RestController
@RequestMapping("/sql")
public class SqlController {
    @Resource
    public UserService userService;

    @GetMapping("/select")
    @DS("oracle")
    public Result select() {
        List<User> list = userService.list();
        System.out.print(list);
        return Result.ofSuccess(list);
    }
}
