package com.heima.controller;


import com.aliyuncs.exceptions.ClientException;
import com.heima.common.MessageConstant;
import com.heima.common.RedisMessageConstant;
import com.heima.pojo.entity.Result;
import com.heima.utils.SMSUtils;
import com.heima.utils.ValidateCodeUtils;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {

    @Autowired
    private JedisPool jedisPool;

    @GetMapping("/send4Order")
    public Result send4Order(String telephone){

        Jedis jedis = jedisPool.getResource();

        String key= RedisMessageConstant.SENDTYPE_ORDER+"_"+telephone;
        String codeInRedis = jedis.get(key);
        if (null!=codeInRedis){
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        Integer code = ValidateCodeUtils.generateValidateCode(6);
        try {
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone,code+"");
            jedis.setex(key,5*60,code+"");
            return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
        } catch (ClientException e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.SEND_VALIDATECODE_FAIL);
        }

    }

    @GetMapping("/send4Login")
    public Result send4Login(String telephone){
        String key=RedisMessageConstant.SENDTYPE_LOGIN+"_"+telephone;
        Result result = gongyong(key, telephone);
        return result;

    }

    private Result gongyong(String key,String telephone){
        Jedis jedis = jedisPool.getResource();
        String codeInRedis = jedis.get(key);
        if (null==codeInRedis){
            return new Result(false,MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        Integer code = ValidateCodeUtils.generateValidateCode(6);
        try {
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone,code+"");
            jedis.setex(key,7*60*60*24,code+"");
            return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
        } catch (ClientException e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.SEND_VALIDATECODE_FAIL);
        }


    }
}
