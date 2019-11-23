package com.heima.dao;

import com.github.pagehelper.Page;
import com.heima.pojo.Menu;

import java.util.List;

public interface MenuDao {
    void add(Menu menu);

    List<Menu> findAll();

    int findById(int id);

    void delete(int id);

    Page<Menu> findPage(String queryString);

    Menu findById1(int id);

    void update(Menu menu);
}
