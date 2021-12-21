package com.example.model;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2021/11/10 11:23
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}