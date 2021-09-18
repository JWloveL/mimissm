package com.bjpowernode.controller;


import com.bjpowernode.pojo.Admin;
import com.bjpowernode.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminAction {
    //切记：在所有的界面层，一定有业务逻辑层的对象
    @Autowired
    AdminService adminService;

    //实现登判断，并进行相应的跳转
    @RequestMapping("/login.action")
    public String login(String name, String pwd, Model model){
        Admin admin = adminService.login(name, pwd);
        if(admin!=null){
            //登录成功
            model.addAttribute("admin",admin);
            return "main";
        }
        else{
            model.addAttribute("errmsg","用户名或密码不正确");

            //登录失败
            return "login";
        }
    }

}
