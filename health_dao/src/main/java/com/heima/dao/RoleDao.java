package com.heima.dao;

import com.github.pagehelper.Page;
import com.heima.pojo.Role;

import java.util.List;

public interface RoleDao {
    void addMenuRole(Integer roleId, Integer menuId);

    void add(Role role);

    void addPermissionRole(Integer roleId, Integer permissionId);

    void deleteByRoleMenuId(Integer roleId);

    void update(Role role);

    Page<Role> findPage(String queryString);

    int findById1(int id);

    int findById2(int id);

    List<Integer> findById3(int id);

    void delete(int id);

    Role findById(int id);

    List<Integer> findById4(int id);
}
