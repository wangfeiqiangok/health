package com.heima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.heima.dao.RoleDao;
import com.heima.exception.HealthException;
import com.heima.pojo.Role;
import com.heima.pojo.entity.PageResult;
import com.heima.pojo.entity.QueryPageBean;
import com.heima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service(interfaceClass = RoleService.class)
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;


    @Override
    public void add(Integer[] menuIds, Integer[] permissionIds, Role role) {
        roleDao.add(role);
        Integer roleId = role.getId();
        if (null!=menuIds){
            for (Integer menuId : menuIds) {
                roleDao.addMenuRole(roleId,menuId);
            }

        }
        if (null!=permissionIds){
            for (Integer permissionId : permissionIds) {
                roleDao.addPermissionRole(roleId,permissionId);
            }
        }
    }

    @Override
    public PageResult<Role> findPage(QueryPageBean queryPageBean) {
        if (!StringUtils.isEmpty(queryPageBean.getQueryString())){

            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }

        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());

        Page<Role> page= roleDao.findPage(queryPageBean.getQueryString());


        return new PageResult<Role>(page.getTotal(),page.getResult());

    }

    @Override
    public void delete(int id) {
        int count1=roleDao.findById1(id);
        int count2=roleDao.findById2(id);
        if (count1>0||count2>0){
            throw new HealthException("角色被使用了.不能删除");
        }
        roleDao.delete(id);

    }

    @Override
    public List<Integer> findRoleMenuByRoleId(int id) {
        return  roleDao.findById3(id);

    }


    @Override
    public void update(Role role, Integer[] menuIds, Integer[] permissionIds) {

        roleDao.update(role);

        Integer roleId = role.getId();
        roleDao.deleteByRoleMenuId(roleId);

        if (menuIds!=null){
            for (Integer menuId : menuIds) {
                roleDao.addMenuRole(roleId,menuId);

            }
        }
        if (permissionIds!=null){
            for (Integer permissionId : permissionIds) {
                roleDao.addPermissionRole(roleId,permissionId);
            }

        }
    }

    @Override
    public Role findById(int id) {
        return roleDao.findById(id);
    }

    @Override
    public List<Integer> findRolePermissionByRoleId(int id) {
        return roleDao.findById4(id);
    }
}
