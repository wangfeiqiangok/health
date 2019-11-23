package com.heima.dao;

import com.github.pagehelper.Page;
import com.heima.pojo.Permission;

import java.util.List;

public interface PermissionDao {
    void add(Permission permission);

    List<Permission> findAll();

    int findById(int id);

    void delete(int id);

    Page<Permission> findPage(String queryString);

    Permission findById1(int id);

    void update(Permission permission);
}
