package com.wizard.demo01.server.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转测试
 * @author wizard_0992
 * @date 2019/12/2 14:50
 */

@Controller
public class SysPageController {

    @RequestMapping("login.html")
    public String login(){
        /*if (SecurityUtils.getSubject().isAuthenticated()){
            return "redirect:index.html";
        }*/
        return "login";
        }

        }