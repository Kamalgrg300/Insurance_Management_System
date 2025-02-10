// UserDao.java
package com.pms.dao;

import com.pms.model.User;

public interface UserDao {
    User login(String username, String password);
    void register(User user);
    void forgetPassword(String email);
}
