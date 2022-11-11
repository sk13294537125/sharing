package com.sharing.cn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping
    public String index() {
        return "index";
    }
}
