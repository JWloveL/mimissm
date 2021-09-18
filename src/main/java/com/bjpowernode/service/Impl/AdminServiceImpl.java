package com.bjpowernode.service.Impl;


import com.bjpowernode.mapper.AdminMapper;
import com.bjpowernode.pojo.Admin;
import com.bjpowernode.pojo.AdminExample;
import com.bjpowernode.service.AdminService;
import com.bjpowernode.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    /*在业务访问层，一定有数据访问层的对象*/
    @Autowired
    AdminMapper adminMapper;
    @Override
    public Admin login(String name, String pwd) {
        AdminExample example = new AdminExample();
        /*
        * 添加条件
        * */
        //添加用户名
        example.createCriteria().andUsernameEqualTo(name);

        List<Admin> list = adminMapper.selectByExample(example);

        if(list.size()>0){
            Admin admin = list.get(0);
            String md5 = MD5Util.getMD5(pwd);
            if(md5.equals(admin.getPassword())){
                return admin;
            }

        }
        return null;

    }
}
