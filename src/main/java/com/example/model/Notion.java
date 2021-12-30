package com.example.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.NotEmpty;

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
    @NotEmpty(message = "标题不能为空")
    @Length(min=6,max=8,message = "标题长度为6-8")
    private String title;
    private String msg;
}
