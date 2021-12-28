package com.example.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2021/12/28 16:16
 */
@Slf4j
@RestController
@RequestMapping("/notion")
public class NotionController {

    @Value("${notion.reboot}")
    private String reboot;
    @Value("${notion.pereId}")
    private String pereId;

    @PostMapping("/addTest")
    public String addTest(@RequestParam String title,
                          @RequestParam String msg) {
        String json = "{\"children\":[{\"object\":\"block\",\"type\":\"heading_2\",\"heading_2\":{\"text\":[{\"type\":\"text\",\"text\":{\"content\":\"" + title + "\"}}]}},{\"object\":\"block\",\"type\":\"paragraph\",\"paragraph\":{\"text\":[{\"type\":\"text\",\"text\":{\"content\":\"" + msg + "\"}}]}}]}";
        String url = "https://api.notion.com/v1/blocks/" + pereId + "/children";
        String result = HttpRequest.patch(url)
                .header("Content-Type", "application/json")
                .header("Authorization", reboot)
                .header("Notion-Version", "2021-05-13")
                .body(json)
                .execute().body();
//        System.out.print(JSONUtil.parseObj(result).toStringPretty());
        return JSONUtil.parseObj(result).toStringPretty();
    }
}
