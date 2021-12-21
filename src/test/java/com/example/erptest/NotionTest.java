package com.example.erptest;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2021/12/21 13:57
 * {"children":[{"object":"block","type":"heading_2","heading_2":{"text":[{"type":"text","text":{"content":"测试推送"}}]}},{"object":"block","type":"paragraph","paragraph":{"text":[{"type":"text","text":{"content":"Hello，WORLD."}}]}}]}
 */
public class NotionTest {

    @Value("${notion.reboot}")
    private String reboot;
    @Value("${notion.pereId}")
    private String pereId;
    @Test
    void addNotion() {
        String reboot = "secret_Cy1GDn3CvfPexU9K86YVZwupV3sosqJHekTIGNwbSqX";
        String pereId = "97b56ba6d681429c91606f562265780d";
        String title = "这是标题";
        String msg = "这是内容";
        String json = "{\"children\":[{\"object\":\"block\",\"type\":\"heading_2\",\"heading_2\":{\"text\":[{\"type\":\"text\",\"text\":{\"content\":\"" + title + "\"}}]}},{\"object\":\"block\",\"type\":\"paragraph\",\"paragraph\":{\"text\":[{\"type\":\"text\",\"text\":{\"content\":\"" + msg + "\"}}]}}]}";
        String url = "https://api.notion.com/v1/blocks/" + pereId + "/children";
        String result = HttpRequest.patch(url)
                .header("Content-Type", "application/json")
                .header("Authorization", reboot)
                .header("Notion-Version", "2021-05-13")
                .body(json)
                .execute().body();
        System.out.print(JSONUtil.parseObj(result).toStringPretty());
    }
}
