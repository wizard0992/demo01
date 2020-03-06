package com.wizard.demo01.server.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转测试
 * @author wizard_0992
 * @date 2019/12/2 14:50
 */

@Controller
public class SysPageController {

    /*@RequestMapping("modules/{module}/{page}.html")
    //@PathVariable 获取“module,page”的参数（@PathVariable 可以将URL中占位符参数{xxx}绑定到处理器类的方法形参中@PathVariable(“xxx“);）
    public String page(@PathVariable String module,@PathVariable String page){

        return "modules/"+module+"/"+page;
    }*/

    @RequestMapping("modules/{module}/{page}.html")
    public String page(@PathVariable String module,@PathVariable String page){
        return "modules/"+module+"/"+page;
    }


    @RequestMapping(value = {"index.html","/"} )
    public String index(){
        return "index";
    }

    @RequestMapping("login.html")
    public String login(){
        if (SecurityUtils.getSubject().isAuthenticated()){
            return "redirect:index.html";
        }
        return "login";
    }

    @RequestMapping("main.html")
    public String main(){
        return "main";
    }

    @RequestMapping("404.html")
    public String notFound(){
        return "404";
    }

}