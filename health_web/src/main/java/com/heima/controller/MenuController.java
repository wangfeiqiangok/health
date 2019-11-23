package com.heima.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.heima.pojo.Menu;
import com.heima.pojo.entity.PageResult;
import com.heima.pojo.entity.QueryPageBean;
import com.heima.pojo.entity.Result;
import com.heima.service.MenuService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Reference
    private MenuService menuService;

    @PostMapping("/add")
    public Result add(@RequestBody Menu menu){
        menuService.add(menu);
        return new Result(true,"添加菜单成功");

    }

    @PostMapping("/findAll")
    public Result findAll(){
        List<Menu>list =menuService.findAll();
        return new Result(true,"查询成功",list);
    }

    @PostMapping("/deleteById")
    public Result deleteById(int id){
        menuService.deleteById(id);
        return new Result(true,"删除成功");
    }

    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult<Menu> pageResult=menuService.findPage(queryPageBean);
        return new Result(true,"分页成功",pageResult);

    }

    @GetMapping("/findById1")
    public Result findById1(int id){
        Menu menu=menuService.findById1(id);
        return new Result(true,"查询成功",menu);
    }

    @PostMapping("/update")
    public  Result update(@RequestBody Menu menu){
        menuService.update(menu);
        return new Result(true,"修改成功");
    }

}
