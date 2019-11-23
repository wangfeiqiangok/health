package com.heima.service;

import com.heima.pojo.CheckItem;
import com.heima.pojo.entity.PageResult;
import com.heima.pojo.entity.QueryPageBean;

import java.util.List;

public interface CheckItemService {
    void add(CheckItem checkItem);

    PageResult<CheckItem> findPage(QueryPageBean queryPageBean);

    void delete(int id);

    CheckItem findById(int id);

    void update(CheckItem checkItem);

    List<CheckItem> findAll();
}
