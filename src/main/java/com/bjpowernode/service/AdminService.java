package com.bjpowernode.service;

import com.bjpowernode.pojo.Admin;
import org.springframework.stereotype.Service;



public interface AdminService {
    //完成登录判断
    Admin login(String name,String pwd);


}
