package com.heima.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.heima.common.MessageConstant;
import com.heima.pojo.Role;
import com.heima.pojo.entity.PageResult;
import com.heima.pojo.entity.QueryPageBean;
import com.heima.pojo.entity.Result;
import com.heima.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Reference
    private RoleService roleService;

    @PostMapping("/add")
    public Result add(@RequestBody Integer[] menuIds, Integer[] permissionIds, Role role){
        roleService.add(menuIds,permissionIds,role);
        return new Result(true,"添加成功");
    }
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
       PageResult<Role> pageResult= roleService.findPage(queryPageBean);
       return new Result(true,"分页成功",pageResult);

    }

    @PostMapping("/deleteById")
    public Result deleteById(int id){
        roleService.delete(id);

        return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);

    }
    @GetMapping("/findById")
    public Result findById(int id){
        Role role=roleService.findById(id);
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,role);
    }

    @GetMapping("/findRoleMenuByRoleId")
    public Result findRoleMenuByRoleId(int id){
        List<Integer> menuIds= roleService.findRoleMenuByRoleId(id);
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,menuIds);
    }
    @GetMapping("/findRolePermissionByRoleId")
    public Result findRolePermissionByRoleId(int id){
        List<Integer> permissions= roleService.findRolePermissionByRoleId(id);
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,permissions);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Role role,Integer[] menuIds, Integer[] permissionIds){
        try {
           roleService.update(role,menuIds,permissionIds);
            return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKITEM_FAIL);
        }

    }



}
