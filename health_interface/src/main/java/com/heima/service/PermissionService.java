package com.heima.service;

import com.heima.pojo.Permission;
import com.heima.pojo.entity.PageResult;
import com.heima.pojo.entity.QueryPageBean;

import java.util.List;

public interface PermissionService {
    void add(Permission permission);

    List<Permission> findAll();

    void deleteById(int id);

    PageResult<Permission> findPage(QueryPageBean queryPageBean);

    Permission findById(int id);

    void update(Permission permission);
}
