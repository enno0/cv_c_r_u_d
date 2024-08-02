package com.cv.cv_c_r_u_d.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/manage_users")
    public String manage_users() {
        return "manage_users";
    }

    @GetMapping("/user-list")
    public String userList() {
        return "user_list";
    }
}
