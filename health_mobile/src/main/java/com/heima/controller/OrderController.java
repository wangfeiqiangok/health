package com.heima.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.heima.common.MessageConstant;
import com.heima.common.RedisMessageConstant;
import com.heima.pojo.entity.Result;
import com.heima.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private JedisPool jedisPool;

    @Reference
    private OrderService orderService;

    @PostMapping("/submit")
    public Result submit (@RequestBody Map<String,String> orderInfo){
        Jedis jedis = jedisPool.getResource();
        String telephone = orderInfo.get("telephone");
        String key= RedisMessageConstant.SENDTYPE_ORDER+"_"+telephone;
        String codeInRedis = jedis.get(key);
        if (null==codeInRedis){
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        if (!orderInfo.get("validateCode").equals(codeInRedis)){

            return new Result(false,MessageConstant.VALIDATECODE_ERROR);
        }

        orderInfo.put("orderType","微信预约");

        int orderId=orderService.addOrder(orderInfo);
        return new Result(true,MessageConstant.ORDER_SUCCESS,orderId);

    }


    @GetMapping("/findById")
    public Result findById(int id){
        Map<String,Object> orderInfo=orderService.findById(id);

        return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,orderInfo);

    }
}
