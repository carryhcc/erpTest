package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2022/1/23 14:39
 */
@Slf4j
@Controller
public class HtmlController {

    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }
}
