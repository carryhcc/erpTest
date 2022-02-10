package com.example.model;

import lombok.Data;

import jakarta.validation.constraints.NotNull;


/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2021/12/28 15:47
 */

@Data
public class Notion {
    private String reboot;
    private String pereId;
    @NotNull(message = "标题不能为空")
    private String title;
    private String msg;
}
