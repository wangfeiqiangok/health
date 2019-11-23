package com.heima.service;

import com.heima.pojo.Role;
import com.heima.pojo.entity.PageResult;
import com.heima.pojo.entity.QueryPageBean;

import java.util.List;

public interface RoleService {
    void add(Integer[] menuIds, Integer[] permissionIds, Role role);

    PageResult<Role> findPage(QueryPageBean queryPageBean);

    void delete(int id);

    List<Integer> findRoleMenuByRoleId(int id);

    void update(Role role, Integer[] menuIds, Integer[] permissionIds);

    Role findById(int id);

    List<Integer> findRolePermissionByRoleId(int id);
}
