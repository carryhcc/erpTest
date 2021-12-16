package com.example.controller;

import cn.hutool.core.text.UnicodeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dao.Dict;
import com.example.mapper.DictMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.stream.Stream;


/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2021/12/16 09:37
 */
@Slf4j
@RestController
@RequestMapping("/dict")
public class DictController {

    @Resource
    public DictMapper dictMapper;

    @PostMapping("/encode")
    public String GuTest(@RequestParam String code) {
        System.out.println("初始语句：" + code);
        //字符串转Unicode符
        String s = UnicodeUtil.toUnicode(code, true);
        System.out.println("中途转换"+s);
        StringBuilder emojiOut = new StringBuilder();
        char[] sList = s.toCharArray();
        for (int i = 0; i < sList.length; i++) {
            emojiOut.append(dict(String.valueOf(sList[i])));
        }
        System.out.println(emojiOut);
        return emojiOut.toString();
    }
    String dict(String str){
        if (str.length()==1){
            QueryWrapper<Dict> wrapper=new QueryWrapper<>();
            wrapper.eq("dict_key",str);
            Dict dict = dictMapper.selectOne(wrapper);
            return dict.getDictValue();
        }else {
            QueryWrapper<Dict> wrapper=new QueryWrapper<>();
            wrapper.eq("dict_value",str);
            Dict dict = dictMapper.selectOne(wrapper);
            return dict.getDictKey();
        }
    }
    @PostMapping("/decode")
    String encodeEmoji(@RequestParam String emoji){
        StringBuilder outPut=new StringBuilder();
        for (int i = 0; i < emoji.length()/2; i++) {
            outPut.append(dict(emoji.substring(2*i,(2*i)+2)));
        }
        return UnicodeUtil.toString(outPut.toString());
    }
}
