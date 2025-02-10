package com.pms.dao;

import com.pms.model.Policy;

public interface PolicyDao {
    void addPolicy(String policyName, String policyDetails, int subCategoryId);
    void viewPolicy(int policyId);
    void updatePolicy(int policyId, String newPolicyName, String newPolicyDetails);
    void deletePolicy(int policyId);
}
