package com.heima.dao;

import com.github.pagehelper.Page;
import com.heima.pojo.Package;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PackageDao {
    void add(Package pkg);

    void addPackageAndCheckgroup(@Param("pkgId") Integer pkgId, @Param("checkgroupId") Integer checkgroupId);

    Page<Package> findPage(String queryString);

    List<Package> findAll();

    Package findById(int id);


    List<Integer> findPackageCheckgroupById(int id);

    Package findByPackageId(int id);

    void update(Package pkg);

    void delete(Integer pkgId);

    List<Map<String, Object>> findPackageCount();

    List<Map<String, Object>> findHotPackage();
}
