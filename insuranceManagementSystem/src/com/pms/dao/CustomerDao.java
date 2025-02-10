// CustomerDao.java
package com.pms.dao;

import com.pms.model.Category;
import com.pms.model.Policy;

import java.util.List;

public interface CustomerDao {
    List<Category> getCategories();
    List<Policy> getPolicies();
    void applyPolicy(int policyId);
}
