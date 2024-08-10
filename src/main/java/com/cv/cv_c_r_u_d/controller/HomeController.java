package com.cv.cv_c_r_u_d.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/email")
    public String email() {
        return "email";
    }

    @GetMapping("/manage_users")
    public String manage_users() {
        return "manage_users";
    }

    @GetMapping("/user-list")
    public String userList() {
        return "user_list";
    }

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String logout() {
        return "home";
    }

    @GetMapping("/manage_tasks")
    public String manage_tasks() {
        return "manage_tasks";
    }

    @GetMapping("/task-list")
    public String taskList() {
        return "task_list";
    }

    @GetMapping("/tyty")
    public String tyty() {
        return "tyty";
    }

}
