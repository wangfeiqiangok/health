package com.heima.service;

import com.heima.pojo.User;

public interface UserService {
    User findByUsername(String username);
}
