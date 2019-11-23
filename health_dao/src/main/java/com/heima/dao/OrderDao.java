package com.heima.dao;

import com.heima.pojo.Order;
import org.apache.ibatis.annotations.Param;


import java.util.List;
import java.util.Map;

public interface OrderDao {

    List<Order> findByCondition(Order order);

    void add(Order order);


    Map<String, Object> findById(int id);

    Integer findOrderCountByDate(String today);

    Integer findOrderCountBetweenDate(@Param("startDate") String startDate, @Param("endDate") String endDate);

    Integer findVisitsCountByDate(String today);

    Integer findVisitsCountAfterDate(String date);
}
