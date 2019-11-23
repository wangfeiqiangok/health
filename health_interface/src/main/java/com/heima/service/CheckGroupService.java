package com.heima.service;

import com.heima.pojo.CheckGroup;
import com.heima.pojo.entity.PageResult;
import com.heima.pojo.entity.QueryPageBean;

import java.util.List;


public interface CheckGroupService {


    PageResult<CheckGroup> findPage(QueryPageBean queryPageBean);

    CheckGroup findById(int id);




    void update(CheckGroup checkGroup, Integer[] checkitemIds);

    void delete(int id);

    void add(CheckGroup checkGroup, Integer[] checkitemIds);

    List<Integer> checkItemIdsByCheckGroupId(int id);

    List<CheckGroup> findAll();

}
