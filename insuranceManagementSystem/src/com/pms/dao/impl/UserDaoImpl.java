// UserDaoImpl.java
package com.pms.dao.impl;

import com.pms.dao.UserDao;
import com.pms.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private List<User> users = new ArrayList<>();

    @Override
    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void register(User user) {
        users.add(user);
        System.out.println("User registered: " + user.getUsername());
    }

    @Override
    public void forgetPassword(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                System.out.println("Password reset instructions sent to: " + email);
                return;
            }
        }
        System.out.println("Email not found.");
    }
}
