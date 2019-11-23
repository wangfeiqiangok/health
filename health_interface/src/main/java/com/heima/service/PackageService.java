package com.heima.service;

import com.heima.pojo.Package;
import com.heima.pojo.entity.PageResult;
import com.heima.pojo.entity.QueryPageBean;

import java.util.List;
import java.util.Map;

public interface PackageService {
    void add(Package pkg, Integer[] checkgroupIds);

    PageResult<Package> findPage(QueryPageBean queryPageBean);

    List<Package> findAll();

    Package findById(int id);


    List<Integer> findPackageCheckgroupById(int id);

    Package findByPackageId(int id);

    void update(Package pkg, Integer[] checkgroupIds);


    List<Map<String, Object>> findPackageCount();
}
