package com.example.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2021/10/28 10:00
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class TestController {
//    @Autowired
//    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

//    @GetMapping("/info")
//    public Result testSelect() {
//        System.out.println(("----- selectAll method test ------"));
//        List<User> userList = userMapper.selectList(null);
//        userList.forEach(System.out::println);
//        return Result.newSuccess("ok");
//    }
//a7493d5d0bf5143bbaa61ffc8f3700a1
//wx15ae82b858b1469f
    @GetMapping("/QrCode")
    public String  downloadQrImage() throws Exception {

        String appId="wx15ae82b858b1469f";
        String appKey="a7493d5d0bf5143bbaa61ffc8f3700a1";
        //        先查询是否存的access_token
        String accessToken = stringRedisTemplate.opsForValue().get("accessToken");
        if(StrUtil.isEmpty(accessToken)){
            String access_token = postToken(appId, appKey);
            //添加到redis 设置过期时间7200秒
            System.out.println("存的为"+access_token);
//            String msgExpiry = RedisKeyPrefix.ACCESS_TOKEN + access_token;
            stringRedisTemplate.opsForValue().set("accessToken",access_token, 7200, TimeUnit.SECONDS);
        }
//        获取接口调用凭证access_token
//        生成二维码
        String tokenQrCode = stringRedisTemplate.opsForValue().get("accessToken");
        System.out.printf("从redis获取的"+tokenQrCode);

//        String tokenQrCode = postToken(appId, appKey);
        String s = generateQrCode("xcxQrImage.png", "pages/purchaseDrug/purchaseDrug", "id=123", tokenQrCode);
        if(StringUtils.isEmpty(s)){
//            return Result.newFail("生成二维码失败!");
            return"生成二维码失败!";
        }
//        return Result.newSuccess("data:image/png;base64,"+s);
        return "data:image/png;base64,"+s;
    }
    /**
     * 接口调用凭证 access_token
     */
    public String postToken(String appId, String appKey) throws Exception {

        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appKey;
        String result = HttpUtil.createGet(url).header(Header.HOST,"api.weixin.qq.com").execute().charset("utf-8").body();
        JSONObject jsonObject = JSONObject.parseObject(result);
        return jsonObject.getString("access_token");
    }
    /**
     * 生成微信小程序二维码
     * @return
     */
    public static String generateQrCode(String filePath, String page, String scene, String accessToken) throws IOException {
        String url="https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+accessToken;
        JSONObject paramJson = new JSONObject();
        paramJson.put("scene",scene);
        paramJson.put("page", page);
        paramJson.put("width", "1280");
        //要打开的小程序版本。正式版为 "release"，体验版为 "trial"，开发版为 "develop"
        paramJson.put("env_version","trial");
        paramJson.put("is_hyaline", false);
        paramJson.put("auto_color", false);
        paramJson.put("check_path",false);
//        byte[] bytes = HttpRequest.post(url)
//                .header(Header.CONTENT_TYPE, "application/json")
//                .header(Header.HOST,"api.weixin.qq.com")
//                .body(paramJson.toString()).execute().bodyBytes();
        InputStream inputStream = HttpRequest.post(url)
                .header(Header.CONTENT_TYPE, "application/json")
                .header(Header.HOST, "api.weixin.qq.com")
                .body(paramJson.toString()).execute().bodyStream();
        byte[] bytes = create( inputStream, 1600, 2200);
        String s = java.util.Base64.getEncoder().encodeToString(bytes);
        return s;
    }
    /**
     * 微信返回小程序二维码下面添加文字
     * @param oldPath
     *            原图片保存路径
     * @param width
     *            定义生成图片宽度
     * @param height
     *            定义生成图片高度
     * @return
     * @throws IOException
     */
    public static byte[] create(InputStream oldPath,int width, int height){
        try {
            /**
             * 防控疫情，人人有责
             *
             * 湖北省新型冠状病毒感染肺炎疫情防控指挥部
             * 湖北省药品监督管理局
             * 联合监制
             */
            String strUp="防控疫情，人人有责";
            String strdown1="湖北省新型冠状病毒感染肺炎疫情防控指挥部";
            String strdown2="湖北省药品监督管理局";
            String strdown3="联合监制";

            Image image = ImageIO.read(oldPath);
//            File file = new File(newPath);
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = bi.createGraphics();
            g2.setBackground(Color.WHITE);
            g2.clearRect(0, 0, width, height);
            //这里减去125是为了防止字和图重合
            g2.drawImage(image, 0, 250, width, height - 600, null);
            /* 设置生成图片的文字样式 * */
            Font font = new Font("Microsoft YaHei", Font.BOLD, 50);
            g2.setFont(font);
            g2.setPaint(Color.BLACK);

            /* 设置字体在图片中的位置 在这里是居中* */
            FontRenderContext context = g2.getFontRenderContext();
            Rectangle2D bounds = font.getStringBounds(strdown1, context);
            double x1 = (width - font.getStringBounds(strdown1, context).getWidth()) / 2;
            double x2 = (width - font.getStringBounds(strdown2, context).getWidth()) / 2;
            double x3 = (width - font.getStringBounds(strdown3, context).getWidth()) / 2;
            double x4 = (width - font.getStringBounds(strUp, context).getWidth())  / 2;
            //double y = (height - bounds.getHeight()) / 2; //Y轴居中
            double y = (height - bounds.getHeight());
            double ascent = -bounds.getY();
            double baseY = y + ascent;

            /* 防止生成的文字带有锯齿 * */
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            /* 在图片上生成文字 * */
            g2.drawString(strdown1, (int) x1, (int) baseY-300);
            g2.drawString(strdown2, (int) x2, (int) baseY-200);
            g2.drawString(strdown3, (int) x3, (int) baseY-100);

            Font fontUp = new Font("Microsoft YaHei", Font.BOLD, 100);
            g2.setFont(fontUp);
            g2.setPaint(Color.BLACK);
            g2.drawString(strUp, (int) x4-210, (int) baseY-2000);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ImageIO.write(bi,"png", stream);
            return stream.toByteArray();
//            ImageIO.write(bi, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
