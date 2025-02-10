package com.pms.dao;

import com.pms.model.Category;
import com.pms.model.Policy;

import java.util.List;

public interface CustomerDao {
    List<Category> getCategories();
    List<Policy> getPolicies();
    
    // Change return type from void to boolean
    boolean applyPolicy(int policyId);
}
