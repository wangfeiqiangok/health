package com.heima.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.heima.common.MessageConstant;
import com.heima.pojo.Package;
import com.heima.pojo.entity.Result;
import com.heima.service.PackageService;
import com.heima.utils.QiNiuUtil;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/package")
public class PackageController {
    @Reference
    private PackageService packageService;

    @GetMapping("/getPackage")
    public Result getPackage(){
        List<Package> list=packageService.findAll();
       list.forEach(pkg ->{
           pkg.setImg("http://"+QiNiuUtil.DOMAIN+"/"+pkg.getImg());
       });
       return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,list);
    }

    @GetMapping("/findById")
    public Result findById(int id){
       Package pkg= packageService.findById(id);
       pkg.setImg("http://"+QiNiuUtil.DOMAIN+"/"+pkg.getImg());

       return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,pkg);
    }

    @GetMapping("/findById1")
    public Result findById1(int id){
        Package pkg=packageService.findByPackageId(id);
        pkg.setImg("http://"+QiNiuUtil.DOMAIN+"/"+pkg.getImg());
        return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,pkg);
    }
}
