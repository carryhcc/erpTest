package com.example.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.example.mapper.WeiboInfoMapper;
import com.example.model.Result;
import com.example.model.WeiboInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2022/4/22 23:08
 */
@Slf4j
@RestController
public class weiboController {

    @Resource
    private WeiboInfoMapper mapper;

    @GetMapping("/selectWeibo")
    @DS("mysql")
    public Result selectPhone(@RequestParam String phone) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("phone", phone);
        String result = HttpUtil.get("https://zy.xywlapi.cc/wbphone", paramMap);
        log.info(result);
        log.info("this is info log;time:{}", System.currentTimeMillis());
        log.warn("this is info log;time:{}", System.currentTimeMillis());
        log.debug("this is info log;time:{}", System.currentTimeMillis());
//        if (JSON.isValid(result)) {
//            return Result.error("已被拦截");
//        }
        JSONObject json = JSON.parseObject(result);
        Integer status = json.getInteger("status");
        String message = json.getString("message");
        if (status == 500) {
            return Result.error(message);
        }
        if (status == 200) {
            WeiboInfo weiboInfo = new WeiboInfo();
            String phone1 = json.getString("phone");
            weiboInfo.setPhone(phone1);
            weiboInfo.setUid("id");
            weiboInfo.setPhoneCity(json.getString("phonediqu"));
            mapper.insert(weiboInfo);
        }
        log.info("执行完毕");
        return Result.success();
    }
    @GetMapping("/weiboAdd")
    @DS("mysql")
    public Result WeiboAdd() {
        String phone ="17798232540";
        String uid ="11111111111";
        String phoneditu ="湖北省武汉市";
        WeiboInfo weiboInfo = new WeiboInfo();
        weiboInfo.setId(IdWorker.getId());
        weiboInfo.setPhone(phone);
        weiboInfo.setUid(uid);
        weiboInfo.setPhoneCity(phoneditu);
        mapper.insert(weiboInfo);
        log.info("this is info log;time:{}", System.currentTimeMillis());
        log.warn("this is info log;time:{}", System.currentTimeMillis());
        log.debug("this is info log;time:{}", System.currentTimeMillis());
        return Result.success();
    }
}
