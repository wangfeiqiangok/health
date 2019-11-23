package com.heima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.heima.dao.OrderSettingDao;
import com.heima.pojo.OrderSetting;
import com.heima.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service(interfaceClass = OrderSettingService.class)
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    private OrderSettingDao orderSettingDao;

    @Override
    public void add(List<OrderSetting> al) {
       //判断是否有值
        if (al!=null){

            for (OrderSetting os : al) {
                int count=orderSettingDao.findByDate(os.getOrderDate());
                if (count>0){
                    //有值则更新
                    orderSettingDao.update(os);


                }else {
                    //无值则插入
                    orderSettingDao.addOrder(os);
                }
            }


        }
    }

    @Override
    public List<OrderSetting> getOrderSettingByMonth(String month) {
        String startDay=month+"-01";
        String endDay=month+"-31";

        return orderSettingDao.getOrderSettingByMonth(startDay,endDay);
    }

    @Override
    public void editNumberByDate(String orderDate, int number) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        OrderSetting os = new OrderSetting(sdf.parse(orderDate),number);

        int count=orderSettingDao.findByDate(os.getOrderDate());
        if (count>0){
            //有值则更新
            orderSettingDao.update(os);


        }else {
            //无值则插入
            orderSettingDao.addOrder(os);
        }



    }
}
