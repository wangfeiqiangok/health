package com.heima.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.heima.dao.CheckDao;
import com.heima.exception.HealthException;
import com.heima.pojo.CheckItem;
import com.heima.pojo.entity.PageResult;
import com.heima.pojo.entity.QueryPageBean;
import com.heima.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service(interfaceClass = CheckItemService.class)
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckDao checkDao;

    @Override
    public void add(CheckItem checkItem) {
        checkDao.add(checkItem);
    }


    @Override
    public PageResult<CheckItem> findPage(QueryPageBean queryPageBean) {
        if (!StringUtils.isEmpty(queryPageBean.getQueryString())){

            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }

        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());

        Page<CheckItem> page= checkDao.findPage(queryPageBean.getQueryString());


        return new PageResult<CheckItem>(page.getTotal(),page.getResult());
    }

    @Override
    public void delete(int id) {
        int count=checkDao.findById(id);
        if (count>0){
            throw new HealthException("检查项被使用了.不能删除");

        }

        checkDao.deleteById(id);
    }

    @Override
    public CheckItem findById(int id) {
        return checkDao.findById1(id);
    }

    @Override
    @Transactional
    public void update(CheckItem checkItem) {
        checkDao.update(checkItem);
    }

    @Override
    public List<CheckItem> findAll() {
        return checkDao.findAll();
    }
}
