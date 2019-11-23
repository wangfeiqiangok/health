package com.heima.dao;

import com.heima.pojo.OrderSetting;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OrderSettingDao {
    int findByDate(Date orderDate);

    void update(OrderSetting os);

    void addOrder(OrderSetting os);

    List<OrderSetting> getOrderSettingByMonth(@Param("startDay") String startDay, @Param("endDay") String endDay);

    OrderSetting findByOrderDate(String orderDate);

    void editReservationsByOrderDate(@Param("num") int num, @Param("orderDate") String orderDate);
}

