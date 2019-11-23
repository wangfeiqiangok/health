package com.heima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.heima.dao.PackageDao;
import com.heima.pojo.Package;
import com.heima.pojo.entity.PageResult;
import com.heima.pojo.entity.QueryPageBean;
import com.heima.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = PackageService.class)
public class PackageServiceImpl implements PackageService {
    @Autowired
    private PackageDao packageDao;

    @Override
    public void add(Package pkg, Integer[] checkgroupIds) {
        packageDao.add(pkg);
        Integer pkgId = pkg.getId();
        if (checkgroupIds!=null){
            for (Integer checkgroupId : checkgroupIds) {
                packageDao.addPackageAndCheckgroup(pkgId,checkgroupId);
            }
        }

    }

    @Override
    public PageResult<Package> findPage(QueryPageBean queryPageBean) {

        if (!StringUtils.isEmpty(queryPageBean.getQueryString())){
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<Package> page=packageDao.findPage(queryPageBean.getQueryString());

        return new PageResult<Package>(page.getTotal(),page.getResult());
    }

    @Override
    public List<Package> findAll() {

        return packageDao.findAll();
    }

    @Override
    public Package findById(int id) {


        return packageDao.findById(id);
    }

    @Override
    public List<Integer> findPackageCheckgroupById(int id) {

        return packageDao.findPackageCheckgroupById(id);
    }

    @Override
    public Package findByPackageId(int id) {
        return packageDao.findByPackageId(id);
    }

    @Override
    public void update(Package pkg, Integer[] checkgroupIds) {
        packageDao.update(pkg);
        Integer pkgId = pkg.getId();
        packageDao.delete(pkgId);
        if (checkgroupIds!=null){
            for (Integer checkgroupId : checkgroupIds) {
                packageDao.addPackageAndCheckgroup(pkgId,checkgroupId);
            }

        }


    }

    @Override
    public List<Map<String, Object>> findPackageCount() {


        return packageDao.findPackageCount();
    }


}
