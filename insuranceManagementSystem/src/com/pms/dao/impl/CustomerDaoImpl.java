package com.pms.dao.impl;

import com.pms.dao.CustomerDao;
import com.pms.model.Category;
import com.pms.model.Policy;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    private List<Category> categories;
    private List<Policy> policies;

    public CustomerDaoImpl(List<Category> categories, List<Policy> policies) {
        this.categories = categories;
        this.policies = policies;
    }

    @Override
    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public List<Policy> getPolicies() {
        return policies;
    }

    @Override
    public boolean applyPolicy(int policyId) {  // Now returns boolean
        for (Policy policy : policies) {
            if (policy.getId() == policyId) {
                System.out.println("Policy applied: " + policy.getName());
                return true; // Return true if policy is applied
            }
        }
        System.out.println("Policy not found.");
        return false; // Return false if policy is not found
    }
}
