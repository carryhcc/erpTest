package com.example.dao;

import cn.hutool.core.lang.hash.Hash;
import cn.hutool.core.map.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2021/12/16 15:54
 */
public class DictMap {
    public static String getCode(String code) {
        //双向map
        HashBiMap<String, String> biMap = HashBiMap.create();
        biMap.put("0","🎃");
        biMap.put("1","🍏");
        biMap.put("2","🌽");
        biMap.put("3","🧅");
        biMap.put("4","🧄");
        biMap.put("5","🥦");
        biMap.put("6","🥬");
        biMap.put("7","🥕");
        biMap.put("8","🥒");
        biMap.put("9","🌶");
        biMap.put("a","🍆");
        biMap.put("b","🥑");
        biMap.put("c","🍅");
        biMap.put("d","🥝");
        biMap.put("e","🥥");
        biMap.put("f","🍍");
        biMap.put("g","🥭");
        biMap.put("h","🍑");
        biMap.put("i","🍒");
        biMap.put("j","🍈");
        biMap.put("k","🍓");
        biMap.put("l","🍉");
        biMap.put("m","🍌");
        biMap.put("n","🍎");
        biMap.put("o","🍋");
        biMap.put("p","🍊");
        biMap.put("q","🍐");
        biMap.put("r","🍭");
        biMap.put("s","🍬");
        biMap.put("t","🍫");
        biMap.put("u","🍿");
        biMap.put("v","🍩");
        biMap.put("w","🍪");
        biMap.put("x","🥮");
        biMap.put("y","🥠");
        biMap.put("z","🍵");
        biMap.put(" ","🥣");
        biMap.put(",","🍼");
        biMap.put(".","🧃");
        biMap.put("，","🧉");
        biMap.put("。","🥛");
        biMap.put("/","🍺");
        biMap.put("=","🍻");
        biMap.put("\\","🍥");
        if (code.length() == 1) {
            String value = biMap.get(code);
            return value;
        } else {
            //通过value值得到key值
            String key = biMap.inverse().get(code);
            return key;
        }
    }
}
