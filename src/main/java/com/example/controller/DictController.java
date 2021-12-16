package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dao.Dict;
import com.example.mapper.DictMapper;
import com.example.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

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

    @Autowired
    public DictMapper dictMapper;

    @PostMapping("/out")
    public void GuTest(@RequestParam String input) {
        String up = input;
        StringBuilder out = new StringBuilder();
        StringBuilder emojiOut = new StringBuilder();
        for (int i = 0; i < up.length(); i++) {
            char c = up.charAt(i);
            out.append(" ").append(Integer.toHexString(c));
        }
        String s = out.toString();
        String[] split = s.split("");
        for (String value : split) {
            emojiOut.append(dict(value));
        }
        System.out.println("初始语句：" + up);
//        System.out.println("中途转换"+out);
        System.out.println(emojiOut);
    }

    @GetMapping("sql")
    public void installSql(){
        for (int i = 0; i < 11; i++) {
            Dict dict = new Dict();
            dict.setKey(String.valueOf(i));
            dict.setValue("📦");
            dictMapper.insert(dict);
            System.out.printf("插入数据"+i);
        }
    }

    public String dict(String str) {
        QueryWrapper<Dict> wrapper=new QueryWrapper<>();
        wrapper.eq("key",str);
        Dict dict = dictMapper.selectOne(wrapper);
        return dict.getValue();
//        HashMap<String, String> dictMap = new HashMap<>();
//        dictMap.put("0", "\uD83C\uDF47");
//        dictMap.put("1", "\uD83C\uDF48");
//        dictMap.put("2", "\uD83C\uDF49");
//        dictMap.put("3", "\uD83C\uDF50");
//        dictMap.put("4", "\uD83C\uDF51");
//        dictMap.put("5", "\uD83C\uDF52");
//        dictMap.put("6", "\uD83C\uDF53");
//        dictMap.put("7", "\uD83C\uDF54");
//        dictMap.put("8", "\uD83C\uDF55");
//        dictMap.put("9", "\uD83C\uDF56");
//        dictMap.put("a", "\uD83C\uDF57");
//        dictMap.put("b", "\uD83C\uDF58");
//        dictMap.put("c", "\uD83C\uDF59");
//        dictMap.put("d", "\uD83C\uDF60");
//        dictMap.put("e", "\uD83C\uDF61");
//        dictMap.put("f", "\uD83C\uDF62");
//        dictMap.put("g", "\uD83C\uDF63");
//        dictMap.put("h", "\uD83C\uDF64");
//        dictMap.put("i", "\uD83C\uDF65");
//        dictMap.put("j", "\uD83C\uDF66");
//        dictMap.put("k", "\uD83C\uDF67");
//        dictMap.put("l", "\uD83C\uDF68");
//        dictMap.put("m", "\uD83C\uDF69");
//        dictMap.put("n", "\uD83C\uDF70");
//        dictMap.put("o", "\uD83C\uDF71");
//        dictMap.put("p", "\uD83C\uDF72");
//        dictMap.put("q", "\uD83C\uDF73");
//        dictMap.put("r", "\uD83C\uDF74");
//        dictMap.put("s", "\uD83C\uDF75");
//        dictMap.put("t", "\uD83C\uDF76");
//        dictMap.put("u", "\uD83C\uDF77");
//        dictMap.put("v", "\uD83C\uDF78");
//        dictMap.put("w", "\uD83C\uDF79");
//        dictMap.put("x", "\uD83C\uDF80");
//        dictMap.put("y", "\uD83C\uDF81");
//        dictMap.put("z", "\uD83C\uDF82");
//        dictMap.put(" ", "\uD83C\uDF83");
//        return dictMap.get(str);
    }
}
