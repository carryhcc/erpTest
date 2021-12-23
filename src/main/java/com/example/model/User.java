package com.example.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2021/11/10 11:23
 */
@Data
@TableName(value = "USER_INFO")
public class User {
    @TableId(value = "ID")
    private Long id;
    @TableField(value = "NAME")
    private String name;
    @TableField(value = "AGE")
    private Integer age;
    @TableField(value = "EMAIL")
    private String email;
}