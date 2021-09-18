package com.bjpowernode.controller;

import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.service.ProductInfoService;
import com.bjpowernode.utils.FileNameUtil;
import com.github.pagehelper.PageInfo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/prod")
public class ProductInfoAction {

    String saveFileName="";
    public static final int PAGE_SIZE=5;
    @Autowired
    ProductInfoService productInfoService;

    //显示全部商品不分页
    @RequestMapping("/getAll")
    public String getAll(Model model){
        List<ProductInfo> list = productInfoService.getAll();
        model.addAttribute("list",list);
        return "product";
    }

    //显示第一页商品5条记录
    @RequestMapping("/split")
    public String split(Model model){

        //得到第一页数据
        PageInfo info = productInfoService.splitPage(1, PAGE_SIZE);
        model.addAttribute("info",info);
        return "product";
    }

    //ajax分页翻页请求
    @ResponseBody
    @RequestMapping("/ajaxSplit")
    public void ajaxSplit(int page, HttpSession session){
        //取得当前page参数的页面的数据
        PageInfo info=productInfoService.splitPage(page,PAGE_SIZE);
        session.setAttribute("info",info);
    }

    //异步ajax文件上传处理
    @ResponseBody
    @RequestMapping("/ajaxImg")
    public Object ajaxImg(MultipartFile pimage, HttpServletRequest request){
        //提取UUID+上传图片的后缀
        saveFileName = FileNameUtil.getUUIDFileName() + FileNameUtil.getFileType(pimage.getOriginalFilename());
        //获取项目中图片的存储路径
        String path = request.getServletContext().getRealPath("/image_big");
        //转存
        try {
            pimage.transferTo(new File(path+File.separator+saveFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject object = new JSONObject();
        object.put("imgurl",saveFileName);

        return object.toString();
    }

    @RequestMapping("/save")
    public String save(ProductInfo info,Model model){
        info.setpImage(saveFileName);
        info.setpDate(new Date());

        int num=-1;
        num=productInfoService.save(info);
        if (num>0){
            model.addAttribute("msg","添加成功");
        }
        else {
            model.addAttribute("msg","添加失败");
        }
        return "forward:/prod/split.action";
    }

    @RequestMapping("/one")
    public String one(int pid, Model model){
        ProductInfo info = productInfoService.getByID(pid);
        model.addAttribute("prod",info);
        return "update";
    }
}
