package com.heima.service;

import com.heima.pojo.Menu;
import com.heima.pojo.entity.PageResult;
import com.heima.pojo.entity.QueryPageBean;

import java.util.List;

public interface MenuService {
    void add(Menu menu);

    List<Menu> findAll();

    void deleteById(int id);

    PageResult<Menu> findPage(QueryPageBean queryPageBean);

    Menu findById1(int id);

    void update(Menu menu);
}
