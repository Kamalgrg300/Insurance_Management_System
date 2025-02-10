package com.pms.dao.impl;

import com.pms.dao.UserDao;
import com.pms.model.User;
import com.pms.model.Admin;
import com.pms.model.Customer;

public class UserDaoImpl implements UserDao {

    @Override
    public void register(User user) {
        // For demonstration, simply print registration
        System.out.println(user.getUsername() + " has been registered.");
    }
    
    @Override
    public User login(String username, String password) {
        // For demonstration, check credentials.
        // If username is "admin" with password "admin", return an Admin.
        // If username is "customer" with password "customer", return a Customer.
        if (username.equals("admin") && password.equals("admin")) {
            return new Admin(username, password, "admin@example.com");
        } else if (username.equals("customer") && password.equals("customer")) {
            return new Customer(username, password, "customer@example.com");
        } else {
            return null;
        }
    }
    
    @Override
    public void forgetPassword(String email) {
        System.out.println("Password reset link sent to: " + email);
    }
}
