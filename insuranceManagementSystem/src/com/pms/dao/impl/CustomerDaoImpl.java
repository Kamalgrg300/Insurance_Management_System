package com.pms.dao.impl;

import com.pms.dao.CustomerDao;
import com.pms.model.SubCategory;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    
    private List<SubCategory> policies;
    
    public CustomerDaoImpl(List<SubCategory> policies) {
        this.policies = policies;
    }
    
    @Override
    public List<SubCategory> getPolicies() {
        return policies;
    }
    
    @Override
    public boolean applyPolicy(int policyId) {
        for (SubCategory policy : policies) {
            if (policy.getSubCategoryId() == policyId) {
                System.out.println("Policy applied: " + policy.getSubCategoryName());
                return true;
            }
        }
        System.out.println("Policy ID " + policyId + " not found.");
        return false;
    }
}
