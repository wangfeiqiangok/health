package com.heima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.heima.common.MessageConstant;

import com.heima.pojo.Permission;
import com.heima.pojo.entity.PageResult;
import com.heima.pojo.entity.QueryPageBean;
import com.heima.pojo.entity.Result;


import com.heima.service.PermissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/quanxian")
public class QuanxianController {
    @Reference
    private PermissionService permissionService;

    @PostMapping("/add")
    public Result add(@RequestBody Permission permission){
        try {
            permissionService.add(permission);
            return new Result(true,"添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"添加失败");
        }

    }

    @PostMapping("/findAll")
    public  Result findAll(){
               List<Permission>list=permissionService.findAll();
               return new Result(true,"查找成功",list);
    }


    @PostMapping("/deleteById")
    public Result deleteById(int id){
        permissionService.deleteById(id);

        return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);

    }

    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult<Permission> pageResult =permissionService.findPage(queryPageBean);
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,pageResult);

    }
    @GetMapping("/findById")
    public Result findById(int id){
        Permission permission= permissionService.findById(id);
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,permission);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Permission permission) {
        try {
            permissionService.update(permission);
            return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }
    }


}
