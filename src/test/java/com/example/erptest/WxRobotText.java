package com.example.erptest;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2021/12/1 14:10
 */
public class WxRobotText {
    @Test
    public void upRobot() {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=";
        String key = "40401cff-9a2a-445e-997d-32943c16059a";
        //内容
        //当前时间
        Date date = DateUtil.date();
        Date yd = DateUtil.parse("2022-01-01");
        long ydDay = DateUtil.between(date, yd, DateUnit.DAY);
        Date cj = DateUtil.parse("2022-02-01");
        long cjDay = DateUtil.between(date, cj, DateUnit.DAY);
        Date qm = DateUtil.parse("2022-04-05");
        long qmDay = DateUtil.between(date, qm, DateUnit.DAY);
        Date ld = DateUtil.parse("2022-05-01");
        long ldDay = DateUtil.between(date, ld, DateUnit.DAY);
        Date dw = DateUtil.parse("2022-06-03");
        long dwDay = DateUtil.between(date, dw, DateUnit.DAY);
        Date zq = DateUtil.parse("2022-09-10");
        long zqDay = DateUtil.between(date, zq, DateUnit.DAY);
        Date gq = DateUtil.parse("2022-10-01");
        long gqDay = DateUtil.between(date, gq, DateUnit.DAY);

        String msg = "  【摸鱼办】提醒您：" + DateUtil.today() + "早上好，摸鱼人！工作再累，一定不要忘记摸鱼哦！有事没事起身去茶水间，去厕所，去廊道走走别老在工位上坐着，钱是老板的,但命是自己的\n" +
                "      距离元旦还有:" + ydDay + "天  \n" +
                "      距离春节还有:" + cjDay + "天 \n" +
                "      距离清明节还有:" + qmDay + "天 \n" +
                "      距离劳动节还有:" + ldDay + "天 \n" +
                "      距离端午节还有:" + dwDay + "天 \n" +
                "      距离中秋节还有:" + zqDay + "天 \n" +
                "      距离国庆节还有:" + gqDay + "天 \n" +
                "      祝愿所有打工仔，都能愉快渡过每一天…\n" +
                "---------------------------------\n" +
                "天气预报\n" + weather();
        HashMap<String, Object> map = new HashMap<>();
        map.put("msgtype", "text");
        HashMap<String, Object> content = new HashMap<>();
        content.put("content", msg);
        String[] arr = {"@all"};
        content.put("mentioned_list", arr);
        map.put("text", content);
        String body = JSONUtil.toJsonStr(map);
        String result = HttpRequest.post(url + key)
                .body(body)
                .execute().body();

        System.out.println(result);
    }

    @Test
    public static String weather() {
//        http://wthrcdn.etouch.cn/weather_mini?city=
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("city", "武汉");
        String url = "http://wthrcdn.etouch.cn/weather_mini";
        String result = HttpUtil.get(url, paramMap);

        JSONObject data = JSONUtil.parseObj(JSONUtil.parseObj(result).getStr("data"));
        String yesterday = data.getStr("yesterday");
        JSONObject day = JSONUtil.parseObj(yesterday);
        String msg = "您所在的城市为" + data.getStr("city") + "\n今天是:" + DateUtil.today() +
//                " 风力:" + day.getStr("fl") +
                " \n天气:" + day.getStr("type") +
                " \n温度：" + day.getStr("high") + day.getStr("low") +
                " \n风向：" + day.getStr("fx") +
                " \n温馨提醒：" + data.getStr("ganmao");
        return msg;
    }

}
