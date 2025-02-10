// AdminDao.java
package com.pms.dao;

import com.pms.model.Category;
import com.pms.model.Policy;

import java.util.List;

public interface AdminDao {
    void addCategory(String name);
    void addPolicy(String name, String description, int categoryId);
    List<Category> getCategories();
    List<Policy> getPolicies();
}
