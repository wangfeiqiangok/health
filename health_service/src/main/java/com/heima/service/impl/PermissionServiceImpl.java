package com.heima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.heima.dao.PermissionDao;
import com.heima.exception.HealthException;

import com.heima.pojo.Permission;
import com.heima.pojo.entity.PageResult;
import com.heima.pojo.entity.QueryPageBean;
import com.heima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;


@Service(interfaceClass = PermissionService.class)
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;


    @Override
    public void add(Permission permission) {
        permissionDao.add(permission);
    }

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public void deleteById(int id) {
        int count=permissionDao.findById(id);
        if (count>0){
            throw new HealthException("权限被使用了.不能删除");
        }
        permissionDao.delete(id);
    }

    @Override
    public PageResult<Permission> findPage(QueryPageBean queryPageBean) {
        if (!StringUtils.isEmpty(queryPageBean.getQueryString())){

            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }

        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());

        Page<Permission> page= permissionDao.findPage(queryPageBean.getQueryString());


        return new PageResult<Permission>(page.getTotal(),page.getResult());
    }

    @Override
    public Permission findById(int id) {
        return permissionDao.findById1(id);
    }

    @Override
    public void update(Permission permission) {
        permissionDao.update(permission);
    }


}
