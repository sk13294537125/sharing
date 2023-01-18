package com.sharing.cn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转
 */
@Controller
@RequestMapping
public class IndexController {

    @RequestMapping("/toIndex")
    public String index() {
        return "index";
    }
}
