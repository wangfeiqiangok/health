package com.heima.dao;

import com.heima.pojo.User;

public interface UserDao {

    User findByUsername(String username);
}
