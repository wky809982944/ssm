package com.wangkaiyi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 用户请求
 */
@Controller
@RequestMapping("user")
public class UserController {
    @RequestMapping("toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("login")
    public String login(String username, String password, HttpSession session) {
        if (username.equalsIgnoreCase("admin")) {
            session.setAttribute("username", username);
            return "redirect:/itemList.action";
        }
        return "login";
    }
}
