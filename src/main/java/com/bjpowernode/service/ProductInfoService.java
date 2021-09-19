package com.bjpowernode.service;

import com.bjpowernode.pojo.ProductInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductInfoService {

    //显示全部商品(不分页)
    List<ProductInfo> getAll();

    PageInfo splitPage(int PageNum,int PageSize);

    int save(ProductInfo info);

    //按主键id查询的方法
    ProductInfo getByID(int pid);

    int update(ProductInfo info);
}
