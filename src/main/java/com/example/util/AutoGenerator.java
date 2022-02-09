package com.example.util;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

/**
 * 代码自动生成器
 *
 * @author zpc
 * @version 1.0
 * @date 2021/11/7 13:59
 */
public class AutoGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://hcc19970824.mysql.rds.aliyuncs.com:3306/rrsp?useUnicode=true&characterEncoding=utf8",
                        "root", "Hcc19970824")
                // 全局配置
                .globalConfig(builder -> {
                    builder.author("hcc") // 设置作者
                            // .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(System.getProperty("user.dir") + "/src/main/java"); // 指定输出目录
//                            .commentDate("yyyy-MM-dd"); //调整时间格式
                })
                // 包配置
                .packageConfig(builder -> {
                    builder.parent("com.example") // 设置父包名
//                            .moduleName("system") // 设置父包模块名
                            .entity("model")
                            .service("service")
                            .serviceImpl("service.impl")
                            .controller("controller")
                            .mapper("mapper")
//                            .xml("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") + "/src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                // 策略配置
                .strategyConfig(builder -> {
                    //这里修改表名
                    builder.addInclude("sync_msg") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_") // 设置过滤表前缀
                            .serviceBuilder()   // service 配置策略
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .entityBuilder()
                            .enableLombok() // 开启Lombok
                            .logicDeleteColumnName("deleted")   // 说明逻辑删除（软删除）是哪个字段
                            .enableTableFieldAnnotation()   // 属性上加说明注解
                            .controllerBuilder()    // controller 策略配置
                            .formatFileName("%sController")
                            .enableRestStyle()  // 开启 RestController
                            .mapperBuilder()    //mapper 策略配置
                            .superClass(BaseMapper.class)   // 继承哪个父类
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()   // @mapper 开启
                            .formatXmlFileName("%sMapper"); // xml 名

                })
//                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
