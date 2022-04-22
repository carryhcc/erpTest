package com.example.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mapper.UserInfoMapper;
import com.example.model.Result;
import com.example.model.UserInfo;
import com.example.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2022/1/11 11:41
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class UserInfoController {

    @Resource
    public UserInfoService userInfoService;
    @Resource
    public UserInfoMapper userInfoMapper;

    @GetMapping("/select")
    @DS("oracle")
    public Result userInfo() {
        LambdaQueryWrapper<UserInfo> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.like(UserInfo::getName, "胡");
        Page<UserInfo> userPage = new Page<>(1, 10);
        IPage<UserInfo> userIPage = userInfoMapper.selectPage(userPage, userLambdaQueryWrapper);
        log.info("总页数：{}", userIPage.getPages());
        log.info("总记录数：{}", userIPage.getTotal());
        userIPage.getRecords().forEach(System.out::println);
        log.info("userIPage:{}", userIPage);
        return Result.success(userIPage);
    }
}
