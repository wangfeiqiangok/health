package com.heima.dao;

import com.github.pagehelper.Page;
import com.heima.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CheckGroupDao {
    void add(CheckGroup checkGroup);

    void addCheckGroupCheckItem(@Param("checkGroupId") Integer groupId, @Param("checkitemId") Integer checkitemId);

    Page<CheckGroup> findByCondition(String queryString);

    CheckGroup findById(int id);



    void update(CheckGroup checkGroup);

    void deleteByCheckGroupId(Integer checkGroupId);

    void delete(int id);

    List<Integer> checkItemIdsByCheckGroupId(int id);

    List<CheckGroup> findAll();
}
