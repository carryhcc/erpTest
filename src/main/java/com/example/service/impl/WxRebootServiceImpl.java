package com.example.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.model.wxRtboot.Articles;
import com.example.service.WxRebootService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2022/1/17 14:12
 */
@Service
public class WxRebootServiceImpl implements WxRebootService {

    @Value("${others.wxReboot.key}")
    private String key;

    @Value("${others.wxReboot.url}")
    private String url;

    @Value("${others.wxReboot.weather}")
    private String weatherUrl;

    public static String weather(String url) {
//        http://wthrcdn.etouch.cn/weather_mini?city=
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("city", "武汉");
//        String url = "http://wthrcdn.etouch.cn/weather_mini";
        String result = HttpUtil.get(url, paramMap);

        JSONObject data = JSONUtil.parseObj(JSONUtil.parseObj(result).getStr("data"));
        //解析今天的天气
        String yesterday = data.getStr("yesterday");
        //解析未来5天的天气
        JSONArray forecast = data.getJSONArray("forecast");
        //下一天
        JSONObject jsonObject1 = forecast.getJSONObject(0);
        //下两天
        JSONObject jsonObject2 = forecast.getJSONObject(1);
        //下三天
        JSONObject jsonObject3 = forecast.getJSONObject(2);
        //下四天
        JSONObject jsonObject4 = forecast.getJSONObject(3);
        //下五天
        JSONObject jsonObject5 = forecast.getJSONObject(4);

        JSONObject day = JSONUtil.parseObj(yesterday);
        return "您所在的城市为" + data.getStr("city") + "\n今天是:" + DateUtil.today() +
//                " 风力:" + day.getStr("fl") +
                " \n天气:" + day.getStr("type") +
                " \n温度：" + day.getStr("high") + day.getStr("low") +
                " \n风向：" + day.getStr("fx") +

                " \n" + jsonObject1.getStr("date")+
                " \n天气:" + jsonObject1.getStr("type") +
                " 温度：" + jsonObject1.getStr("high") + jsonObject1.getStr("low") +
                " 风向：" + jsonObject1.getStr("fengxiang") +

                " \n" + jsonObject2.getStr("date")+
                " \n天气:" + jsonObject2.getStr("type") +
                " 温度：" + jsonObject2.getStr("high") + jsonObject2.getStr("low") +
                " 风向：" + jsonObject2.getStr("fengxiang") +

                " \n" + jsonObject3.getStr("date")+
                " \n天气:" + jsonObject3.getStr("type") +
                " 温度：" + jsonObject3.getStr("high") + jsonObject3.getStr("low") +
                " 风向：" + jsonObject3.getStr("fengxiang") +

                " \n" + jsonObject4.getStr("date")+
                " \n天气:" + jsonObject4.getStr("type") +
                " 温度：" + jsonObject4.getStr("high") + jsonObject4.getStr("low") +
                " 风向：" + jsonObject4.getStr("fengxiang") +

                " \n" + jsonObject5.getStr("date")+
                " \n天气:" + jsonObject5.getStr("type") +
                " 温度：" + jsonObject5.getStr("high") + jsonObject5.getStr("low") +
                " 风向：" + jsonObject5.getStr("fengxiang") +

                " \n温馨提醒：" + data.getStr("ganmao");

    }

    @Override
    public void moFish() {
        //内容
        //当前时间
        Date date = DateUtil.date();
        Date yd = DateUtil.parse("2023-01-01");
        long ydDay = DateUtil.between(date, yd, DateUnit.DAY);
        Date cj = DateUtil.parse("2023-01-22");
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
//                "      距离清明节还有:" + qmDay + "天 \n" +
//                "      距离劳动节还有:" + ldDay + "天 \n" +
                "      距离端午节还有:" + dwDay + "天 \n" +
                "      距离中秋节还有:" + zqDay + "天 \n" +
                "      距离国庆节还有:" + gqDay + "天 \n" +
                "      距离元旦还有:" + ydDay + "天  \n" +
                "      距离春节还有:" + cjDay + "天 \n" +
                "      祝愿所有打工仔，都能愉快渡过每一天…\n" +
                "---------------------------------\n" +
                "---记得订餐---记得订餐---记得订餐---\n" +
                "---------------------------------\n" +
                "天气预报\n" + weather(weatherUrl);
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

//        保存到notion
//        NotionInstallUtil.addNotion("摸鱼",msg);
        System.out.println(result);
    }

    @Override
    public void runText(String msg) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("msgtype", "text");
        HashMap<String, Object> content = new HashMap<>();
        content.put("content", msg);
        String[] arr = {"@all"};
        content.put("mentioned_list", arr);
        map.put("text", content);
        run(map);
    }

    @Override
    public void runLogTimeMsg() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("msgtype", "news");
        HashMap<String, Object> news = new HashMap<>();
        ArrayList<Articles> articlesList = new ArrayList<>();
        Articles articles = new Articles();
        articles.setTitle("logTime");
        articles.setDescription("每日填写logTime！！！");
        articles.setUrl("https://jira.yyjzt.com/secure/Tempo.jspa#/reports/report/7f70b14d-f568-40e2-92bf-73de3f50a6d6?columns=WORKED_COLUMN&dateDisplayType=days&from=2022-05-01&groupBy=worker&periodType=CURRENT_PERIOD&subPeriodType=MONTH&teamId=14&to=2022-05-31&viewType=TIMESHEET");
        articles.setPicurl("https://img.jk.com/home/home_icon.png");
        articlesList.add(articles);
        news.put("articles", articlesList);
        map.put("news", news);
        run(map);
    }

    /**
     * 调用接口
     */
    void run(HashMap<String, Object> map) {
        String body = JSONUtil.toJsonStr(map);
        HttpRequest.post(url + key)
                .body(body)
                .execute().body();
    }
}
