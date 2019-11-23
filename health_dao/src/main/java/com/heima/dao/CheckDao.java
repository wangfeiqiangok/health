package com.heima.dao;

import com.github.pagehelper.Page;
import com.heima.pojo.CheckItem;

import java.util.List;

public interface CheckDao {
    void add(CheckItem checkItem);

    Page<CheckItem> findPage(String queryString);

    int findById(int id);

    void deleteById(int id);

    CheckItem findById1(int id);

    void update(CheckItem checkItem);

    List<CheckItem> findAll();
}
