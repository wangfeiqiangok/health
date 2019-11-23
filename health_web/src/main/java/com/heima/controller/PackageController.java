package com.heima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.heima.common.MessageConstant;
import com.heima.common.RedisConstant;
import com.heima.pojo.Package;
import com.heima.pojo.entity.PageResult;
import com.heima.pojo.entity.QueryPageBean;
import com.heima.pojo.entity.Result;
import com.heima.service.PackageService;
import com.heima.utils.QiNiuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/package")
public class PackageController {
    @Autowired
    private JedisPool jedisPool;

    @Reference
    private PackageService packageService;

    @PostMapping("/upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile){
        String originalFilename = imgFile.getOriginalFilename();
        String endFilename = originalFilename.substring(originalFilename.lastIndexOf("."));

        UUID uuid = UUID.randomUUID();

        String fileName=uuid+endFilename;

        HashMap<String,String> hm = new HashMap();
        hm.put("domain",QiNiuUtil.DOMAIN);
        hm.put("imgName",fileName);

        try {
            QiNiuUtil.uploadViaByte(imgFile.getBytes(),fileName);
            //如果图片上传成功就存到redis中
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,hm);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

    @PostMapping("/add")
    public Result add(@RequestBody Package pkg ,Integer[] checkgroupIds ){
        packageService.add(pkg,checkgroupIds);

        //如果添加套餐成功就存到数据库中去
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,pkg.getImg());

        return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);

    }

    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
       PageResult<Package> pageResult= packageService.findPage(queryPageBean);
       return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,pageResult);

    }


//    public static void main(String[] args) {
//
//        String originalFilename="xxxx.jpg";
//
//        String endfilename = originalFilename.substring(originalFilename.lastIndexOf("."));
//        System.out.println(endfilename);
//    }


    @GetMapping("/findById")
    public Result findById(int id){
        Package pkg=packageService.findByPackageId(id);
        return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,pkg);

    }
    @GetMapping("/findPackageCheckgroupById")
    public Result findPackageCheckgroupById( int id){
       List<Integer>checkgroupIds= packageService.findPackageCheckgroupById(id);
       return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,checkgroupIds);

    }

    @PostMapping("/update")
    public Result update(@RequestBody Package pkg,Integer[] checkgroupIds){
        packageService.update(pkg,checkgroupIds);
        return new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);

    }


}
