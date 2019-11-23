package com.heima.service;

import com.heima.pojo.OrderSetting;

import java.text.ParseException;
import java.util.List;

public interface OrderSettingService {
    void add(List<OrderSetting> al);

    List<OrderSetting> getOrderSettingByMonth(String month);

    void editNumberByDate(String orderDate, int number) throws ParseException;
}
