// AdminDaoImpl.java
package com.pms.dao.impl;

import com.pms.dao.AdminDao;
import com.pms.model.Category;
import com.pms.model.Policy;

import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {
    private List<Category> categories = new ArrayList<>();
    private List<Policy> policies = new ArrayList<>();

    @Override
    public void addCategory(String name) {
        categories.add(new Category(categories.size() + 1, name));
    }

    @Override
    public void addPolicy(String name, String description, int categoryId) {
        policies.add(new Policy(policies.size() + 1, name, description));
    }

    @Override
    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public List<Policy> getPolicies() {
        return policies;
    }
}
