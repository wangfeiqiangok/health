package com.heima.service;

import com.heima.exception.HealthException;

import java.util.Map;

public interface OrderService  {
    int addOrder(Map<String, String> orderInfo)throws HealthException;

    Map<String, Object> findById(int id);
}
