package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.model.Dict;
import com.example.model.DictCache;
import com.example.mapper.DictMapper;
import com.example.util.UnicodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2021/12/16 09:37
 */
@Slf4j
@RestController
@RequestMapping("/emoji")
public class EmojiController {

    @Resource
    public DictMapper dictMapper;

    /**
     * 加密
     * @param code
     * @return
     */
    @PostMapping("/encode")
    public String encodeEmoji(@RequestParam String code) {
        log.info("初始语句：{}",code);
        //字符串转Unicode符
        String s = UnicodeUtil.toUnicode(code, true);
        log.info("中途转换：{}",code);
        StringBuilder emojiOut = new StringBuilder();
        char[] sList = s.toCharArray();
        for (char c : sList) {
//            System.out.println("加密数据为"+sList[i]);
            emojiOut.append(DictCache.getValue(String.valueOf(c)));
        }
        log.info("emojiOut：{}",emojiOut);
        return emojiOut.toString();
    }

    String dict(String str) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        if (str.length() == 1) {
            wrapper.eq("dict_key", str);
            wrapper.eq("status", 0);
            Dict dict = dictMapper.selectOne(wrapper);
            return dict.getDictValue();
        } else {
            wrapper.eq("dict_value", str);
            wrapper.eq("status", 0);
            Dict dict = dictMapper.selectOne(wrapper);
            return dict.getDictKey();
        }
    }

    /**
     * 解密
     * @param emoji
     * @return
     */
    @PostMapping("/decode")
    String decodeEmoji(@RequestParam String emoji) {
        StringBuilder outPut = new StringBuilder();
        for (int i = 0; i < emoji.length() / 2; i++) {
            outPut.append(DictCache.getValue(emoji.substring(2 * i, (2 * i) + 2)));
        }
        return UnicodeUtil.toString(outPut.toString());
    }
}
