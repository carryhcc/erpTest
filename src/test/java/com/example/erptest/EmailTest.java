package com.example.erptest;

import cn.hutool.extra.mail.MailUtil;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2021/12/14 15:11
 */

public class EmailTest {
    @Test
    public void mailUp() {
        MailUtil.send("hu.cc@qq.com", "测试", "邮件来自Hutool工具类测试", false);
    }
}
