package com.heima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.heima.common.MessageConstant;
import com.heima.pojo.CheckItem;
import com.heima.pojo.entity.PageResult;
import com.heima.pojo.entity.QueryPageBean;
import com.heima.pojo.entity.Result;
import com.heima.service.CheckItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/check")
public class CheckItemController {

    @Reference
    private CheckItemService checkItemService;

    @PostMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
        try {
            checkItemService.add(checkItem);
            return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();

            return new Result(false,MessageConstant.ADD_CHECKITEM_FAIL);
        }

    }

    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult<CheckItem> pageResult=checkItemService.findPage(queryPageBean);

        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,pageResult);
    }

    @PostMapping("/deleteById")
    public Result deleteById(int id){
        checkItemService.delete(id);

        return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);

    }

    @GetMapping("/findById")
   public Result findById(int id){
       CheckItem checkItem= checkItemService.findById(id);
       return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
   }

   @PostMapping("/update")
   public Result update(@RequestBody CheckItem checkItem){
       try {
           checkItemService.update(checkItem);
           return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);
       } catch (Exception e) {
           e.printStackTrace();
           return new Result(false,MessageConstant.EDIT_CHECKITEM_FAIL);
       }

   }

   @PostMapping("/findAll")
   public Result findAll(){
        List<CheckItem> list=checkItemService.findAll();
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
   }


}
