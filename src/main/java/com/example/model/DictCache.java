package com.example.model;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.google.common.collect.HashBiMap;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2021/12/16 16:51
 */
@Slf4j
@Component
public class DictCache {

    public static HashBiMap<String, String> biMap = HashBiMap.create();;


    @PostConstruct//优先执行
    public static  void init() {
        ExcelReader reader = ExcelUtil.getReader("/Users/cchu/IdeaProjects/erpTest/doc/dict.xlsx");
        List<Map<String,Object>> readAll = reader.readAll();
        for (Map<String, Object> stringObjectMap : readAll) {
            JSONObject jsonObject = JSONUtil.parseObj(stringObjectMap);
            biMap.put(String.valueOf(jsonObject.get("dictKey")), String.valueOf(jsonObject.get("dictValue")));
        }
        log.info("BiMap的缓存添加成功");
        }
    @PreDestroy
    public void destroy() {
        //系统运行结束
    }

    public static String getValue(String code) {
        if (code.length() == 1) {
            return biMap.get(code);
        } else {
            //通过value值得到key值
            return biMap.inverse().get(code);
        }
    }

    /**
     * //每2小时执行一次缓存
     */
//    @Scheduled(cron = "0 0 0/2 * * ?")
//      public void testOne() {init();}
}
