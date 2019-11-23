package com.heima.controller;



import com.heima.common.MessageConstant;
import com.heima.pojo.entity.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getLoginUsername")
    public Result getLoginUsername(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new Result(true, MessageConstant.GET_USERNAME_SUCCESS,username);
    }
}
