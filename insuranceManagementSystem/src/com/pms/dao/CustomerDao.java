package com.pms.dao;

import com.pms.model.SubCategory;
import java.util.List;

public interface CustomerDao {
    List<SubCategory> getPolicies();
    boolean applyPolicy(int policyId);
}
