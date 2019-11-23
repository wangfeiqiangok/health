package com.heima.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.heima.common.MessageConstant;
import com.heima.pojo.OrderSetting;
import com.heima.pojo.entity.Result;
import com.heima.service.OrderSettingService;
import com.heima.utils.POIUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingContrller {

    @Reference
    private OrderSettingService orderSettingService;

    @RequestMapping("/upload")
    public Result upload(@RequestParam("excelFile") MultipartFile excelFile){

        try {
            List<String[]> strings = POIUtils.readExcel(excelFile);
            // 转成List<ordersetting>
            List<OrderSetting> al = new ArrayList<OrderSetting>();
            OrderSetting os = null;
            SimpleDateFormat sdf = new SimpleDateFormat(POIUtils.DATE_FORMAT);
            for (String[] string : strings) {
                // 每一行，每一条记录，实体对象
                os = new OrderSetting(sdf.parse(string[0]), Integer.valueOf(string[1]));
                al.add(os);
            }

            orderSettingService.add(al);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
        return  new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);

    }

    @GetMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String month){
        List<OrderSetting> list=orderSettingService.getOrderSettingByMonth(month);
        return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,list);
    }

    @PostMapping("/editNumberByDate")
    public Result editNumberByDate(String orderDate,int number) {
        try {
            orderSettingService.editNumberByDate(orderDate,number);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ORDERSETTING_FAIL);
        }
        return new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
    }

}
