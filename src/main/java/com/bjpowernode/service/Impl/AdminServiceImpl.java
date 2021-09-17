package com.bjpowernode.service.Impl;

import com.bjpowernode.mapper.AdminMapper;
import com.bjpowernode.pojo.Admin;
import com.bjpowernode.pojo.AdminExample;
import com.bjpowernode.service.AdminService;
import com.bjpowernode.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;
    @Override
    public Admin login(String name, String pwd) {

        AdminExample example=new AdminExample();
        //添加用戶名a_name條件
        example.createCriteria().andANameEqualTo(name);
        List<Admin> admins = adminMapper.selectByExample(example);
        if(admins.size()>0){
            Admin admin=admins.get(0);
            String password = admin.getaPass();
            if(password.equals(MD5Util.getMD5(pwd))){
                return admin;
            }
        }
        return null;
    }
}
