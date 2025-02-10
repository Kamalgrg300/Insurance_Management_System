package com.pms.dao;

import com.pms.model.User;

public interface UserDao {
    void register(User user);
    User login(String username, String password);
    void forgetPassword(String email);
}
