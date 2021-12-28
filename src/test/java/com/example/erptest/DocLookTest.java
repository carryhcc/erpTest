package com.example.erptest;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.model.TestUser;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2021/11/1 16:54
 */
public class DocLookTest {
    @Test
    void look() throws Exception {
        ExcelReader reader = ExcelUtil.getReader("/Users/cchu/IdeaProjects/erpTest/doc/test.xlsx");
//        List<List<Object>> readAll = reader.read();
        List<TestUser> all = reader.readAll(TestUser.class);
        System.out.println(Arrays.toString(all.toArray()));
        String driverClassName = "com.mysql.cj.jdbc.Driver";    //启动驱动
        String url = "jdbc:mysql://hcc19970824.mysql.rds.aliyuncs.com:3306/rrsp?serverTimezone = GMT%2B8";    //设置连接路径
        String username = "root";    //数据库用户名
        String password = "Hcc19970824";    //数据库连接密码
        Connection con = null;        //连接
        PreparedStatement pstmt = null;    //使用预编译语句
        ResultSet rs = null;    //获取的结果集
        Class.forName(driverClassName); //执行驱动
        con = DriverManager.getConnection(url, username, password); //获取连接
        String sql = "INSERT INTO test_user VALUES(?,?,?,?)"; //设置的预编译语句格式
        for (TestUser testUser : all) {
            pstmt = con.prepareStatement(sql);
            int c = RandomUtil.randomInt(1, 100);
            pstmt.setString(1, String.valueOf(c));
            pstmt.setString(2, testUser.get姓名());
            pstmt.setString(3, testUser.get年龄().toString());
            pstmt.setString(4, testUser.get性别());
            pstmt.executeUpdate();
        }
        //关闭资源,倒关
        if (rs != null) rs.close();
        if (pstmt != null) pstmt.close();
        if (con != null) con.close();  //必须要关
    }
}
