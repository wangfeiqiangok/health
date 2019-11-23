package com.heima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.heima.dao.MenuDao;
import com.heima.exception.HealthException;
import com.heima.pojo.Menu;
import com.heima.pojo.entity.PageResult;
import com.heima.pojo.entity.QueryPageBean;
import com.heima.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

@Service(interfaceClass = MenuService.class)
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Override
    public void add(Menu menu) {
        menuDao.add(menu);
    }

    @Override
    public List<Menu> findAll() {
        return menuDao.findAll();
    }

    @Override
    public void deleteById(int id) {
        int count=menuDao.findById(id);
        if (count>0){
            throw new HealthException("菜单被使用了.不能删除");
        }
        menuDao.delete(id);

    }

    @Override
    public PageResult<Menu> findPage(QueryPageBean queryPageBean) {
        if (!StringUtils.isEmpty(queryPageBean)){
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }

        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());

        Page<Menu> page= menuDao.findPage(queryPageBean.getQueryString());


        return new PageResult<Menu>(page.getTotal(),page.getResult());

    }

    @Override
    public Menu findById1(int id) {
        Menu menu=menuDao.findById1(id);
        return menu;
    }

    @Override
    public void update(Menu menu) {
        menuDao.update(menu);

    }
}
