package com.fxr.myblog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author fanXiaoRui
 * @date 2020/10/3 9:51
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }

    @GetMapping("/archives")
    public String archives() {
        return "archives";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/tags")
    public String tags() {
        return "tags";
    }

    @GetMapping("/type")
    public String type() {
        return "type";
    }

//    @GetMapping("/blogs")
//    public String blogs() {
//        return "blogs";
//    }

//    @GetMapping("/blogs-input")
//    public String blogsInput() {
//        return "blogs-input";
//    }
}
