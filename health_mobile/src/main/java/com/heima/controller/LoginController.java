package com.heima.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.heima.common.MessageConstant;
import com.heima.common.RedisMessageConstant;
import com.heima.pojo.Member;
import com.heima.pojo.entity.Result;
import com.heima.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private JedisPool jedisPool;
    @Reference
    private MemberService memberService;

    @PostMapping("/check")
    public Result check(@RequestBody Map<String,String> map, HttpServletResponse response){
        String telephone = map.get("telephone");
        String code = map.get("validateCode");

        Jedis jedis = jedisPool.getResource();
        String key= RedisMessageConstant.SENDTYPE_LOGIN+"_"+telephone;
        String codeInRedis = jedis.get(key);
        if (!codeInRedis.equals(code)){
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
        Member member=memberService.findByTelephone(telephone);
        if (member==null){
            member=new Member();
            member.setPhoneNumber(telephone);
            member.setRegTime(new Date());

            memberService.add(member);
        }

        Cookie cookie=new Cookie("login_telephone",telephone);
        cookie.setPath("/");
        cookie.setMaxAge(30*24*60*60);
        response.addCookie(cookie);
        return new Result(true,MessageConstant.LOGIN_SUCCESS);


    }
}
