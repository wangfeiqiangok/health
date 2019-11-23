package com.heima.job;

import com.heima.common.RedisConstant;
import com.heima.utils.QiNiuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import java.util.Set;

@Component
public class ClearImgJob {
    @Autowired
    private JedisPool jedisPool;

    public void clearImg(){

        Set<String> set=jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES,RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        String[] strings = set.toArray(new String[]{});
//
//        for (String pic : set) {
//           QiNiuUtil.removeFiles(pic);
//           jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,pic);
//        }
        QiNiuUtil.removeFiles(strings);
        jedisPool.getResource().del(RedisConstant.SETMEAL_PIC_RESOURCES,RedisConstant.SETMEAL_PIC_DB_RESOURCES);

    }
    public static void main(String[] args) {
        ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:spring_job.xml");
    }
}
