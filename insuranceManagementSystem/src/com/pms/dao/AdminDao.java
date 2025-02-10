package com.pms.dao;

import com.pms.model.Category;
import com.pms.model.Policy;
import java.util.List;

public interface AdminDao {
    void addCategory(String name);
    void addSubCategory(int categoryId, String name, String description);
    List<Category> getCategories();
    List<Policy> getPolicies();
    
    // Methods for editing
    void updateCategory(int categoryId, String newName);
    void updateSubCategory(int subCategoryId, String newName);
    void updatePolicy(int policyId, String newPolicyName, String newPolicyDetails);
}
