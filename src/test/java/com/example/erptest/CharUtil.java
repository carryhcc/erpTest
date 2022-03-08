package com.example.erptest;

import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2021/12/28 09:50
 */
@Slf4j
public class CharUtil {

    @Test
    public void selectChar() {
        log.info("1111111");
        log.error("222222");
        log.warn("3333333");
        log.debug("444444");
        String formatBetween = DateUtil.formatBetween(32536000L *1000, BetweenFormatter.Level.MINUTE);
        System.out.println(formatBetween);
    }

}


