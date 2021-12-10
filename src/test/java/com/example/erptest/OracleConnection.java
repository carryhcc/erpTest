package com.example.erptest;
import org.junit.jupiter.api.Test;

import java.sql.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2021/10/28 10:02
 */
public class OracleConnection {
    public static void getConnection() throws Exception {
        String url="jdbc:oracle:thin:@10.7.2.234:1521/hysdyf";
        String username="hysdyf";
        String password="lBNYLKuLIhyrNnIV";
        String driver="oracle.jdbc.driver.OracleDriver";
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, username, password);
            Statement state = con.createStatement();   //容器
            String sql = "select * from FORMINSTANCE WHERE FORMID='489kvg3k0jmmujld'";   //SQL语句
            ResultSet resultSet = state.executeQuery(sql);         //将sql语句上传至数据库执行
            while (resultSet.next()) {
//                System.out.println(resultSet.getString(10));
                Blob picture = resultSet.getBlob(10);
                String base64InBlob = BlobAndBase64Util.getBase64InBlob(picture);
                byte[] result = org.apache.commons.codec.binary.Base64.decodeBase64(base64InBlob.getBytes());
                System.out.println(new String(result));
                return;
            }
    }
    @Test
     void play() throws Exception {
        OracleConnection.getConnection();
    }
    @Test
    void demo1(){
        System.out.println("demo1");
    //  划水
        System.out.println();

    }
}
