package com.example.erptest;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;

import com.alibaba.fastjson.JSONObject;
import com.example.util.Base64Util;
import com.example.util.FileUtil;
import org.bouncycastle.util.encoders.Base64Encoder;
import org.junit.Test;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2021/11/18 02:51
 */
public class WxText {

    @Test
    public void login(){
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + "wx21ed1e23ee0fdc46" + "&secret=" + "e8ea94c4373281399ac4af1525a673fd" + "&js_code=" + "001nSf000OQwNM1LMi200u5m3G0nSf0p" + "&grant_type=authorization_code";
        String result = HttpUtil.createGet(url).execute().charset("utf-8").body();
        System.out.println(result);
        // 解析json
        HashMap<Object, Object> map = new HashMap<>();
        map.put("openid", ((JSONObject) JSONObject.parse(result)).get("openid"));
        map.put("session_key", ((JSONObject) JSONObject.parse(result)).get("session_key"));
        System.out.println(map.toString());
    }
    @Test
    public void token(){
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + "wx21ed1e23ee0fdc46" + "&secret=" + "e8ea94c4373281399ac4af1525a673fd";
        String result = HttpUtil.createGet(url).
                execute().charset("utf-8").body();
        JSONObject jsonObject = JSONObject.parseObject(result);
        String access_token = jsonObject.getString("access_token");
        System.out.println(access_token);
    }
    @Test
    public void url(){
        String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+
                "51_Rjoi7bqm4KZEto4U6NB_LyZsVPGV72_JkdLAgrgK7wbyE5RfV22etB5oO_MODOdz-ExosO_YPAmT1BipBF0r5cdXNQv3WFSiXh1ag2B1jdaiG_uDtnl9LufwBUBuw396kjJwOPnsWMObXOHTETMjACAJYR";
//        paramsMap.put("page", "pages/purchaseDrug/purchaseDrug");
        JSONObject paramJson = new JSONObject();
        //这就是你二维码里携带的参数 String型  名称不可变
        paramJson.put("scene", "scene");
        //注意该接口传入的是page而不是path
        paramJson.put("page", "");
        paramJson.put("width", "200");
        paramJson.put("auto_color", false);
        byte[] bytes = HttpRequest.post(url).header(Header.CONTENT_TYPE,"application/json") .body(paramJson.toString()).execute().bodyBytes();
        String s = Base64.getEncoder().encodeToString(bytes);
        s="data:image/png;base64,"+s;
        System.out.println(s);
    }
//    @Test
//    public void baiDuOcrCard(@RequestParam("file") MultipartFile file) {
//        File fileAuto = MultipartFileToFileUtil.multipartFileToFile(file);
//        String idCard = BaiDuOcrUtils.IdCardUtil.idCard(BaiDuOcrUtils.getAuth(),fileAuto.getPath());
//        MultipartFileToFileUtil.fIleDelete(fileAuto);
//        JSONObject cardInfoJson = JSONObject.parseObject(idCard);
//        System.out.println(cardInfoJson);
//     }


    @Test
    public void test(){
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + "RNB8jiOApDiH8lvKA7ddcvEg"
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + "aFKNBPI05yjqHjAtZupmjxc29PxMbHd7";

        try {
            String result = HttpRequest.get(getAccessTokenUrl)
                    .header("host", "aip.baidubce.com")
                    .execute().body();
            JSONObject jsonObject = JSONObject.parseObject(result);
            System.out.println(jsonObject.getString("access_token"));

        }catch (Exception e) {
            System.err.print("获取token失败！");
            e.printStackTrace(System.err);
        }
    }

    @Test

    public void test1(){
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";
        String token = "24.d4cbb580649c29c7c63b7adfd49520ae.2592000.1639782479.282335-24657052";
        try {
            byte[] imgData = FileUtil.readFileByBytes("/Users/cchu/Downloads/1.jpeg");
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
            url += "?access_token=" + token;

            String param = "id_card_side=" + "front" + "&image=" + imgParam;

            String result = HttpRequest.post(url)
                    .header("host", "aip.baidubce.com")
                    .body(param.getBytes(StandardCharsets.UTF_8))
                    .execute().body();

            System.out.println(result);
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            //String accessToken = "[调用鉴权接口获取的token]";

            //String result = com.example.util.HttpUtil.post(url, token, param);
           // System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    }



