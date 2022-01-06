package com.example.controller;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mapper.UserMapper;
import com.example.model.Result;
import com.example.model.User;
import com.example.service.UserService;
import com.example.util.IdWorker;
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
    @Resource
    public UserMapper userMapper;

    @GetMapping("/select")
    @DS("oracle")
    public Result select() {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.like(User::getName , "胡");
//        List<User> users = userMapper.selectList(userLambdaQueryWrapper);
        Page<User> userPage = new Page<>(1 , 10);
        IPage<User> userIPage = userMapper.selectPage(userPage , userLambdaQueryWrapper);
        System.out.println("总页数： "+userIPage.getPages());
        System.out.println("总记录数： "+userIPage.getTotal());
        userIPage.getRecords().forEach(System.out::println);
        System.out.print(userIPage);
        return Result.success(userIPage);
    }
    @GetMapping("/update")
    @DS("oracle")
    public Result update() {
        List<User> list = userService.list();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setId(IdWorker.getId());
        }
        return Result.success();
    }
}
