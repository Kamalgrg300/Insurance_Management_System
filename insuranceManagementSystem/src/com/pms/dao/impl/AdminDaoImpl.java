package com.pms.dao.impl;

import com.pms.dao.AdminDao;
import com.pms.model.Category;
import com.pms.model.Policy;
import com.pms.model.SubCategory;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {
    private List<Category> categories = new ArrayList<>();
    private List<Policy> policies = new ArrayList<>();
    
    // Constructor to initialize categories with the specified subcategories
    public AdminDaoImpl() {
        initializeCategories();
    }
    
    private void initializeCategories() {
        // Create main categories
        Category lifeInsurance = new Category(1, "Life Insurance");
        Category motorInsurance = new Category(2, "Motor Vehicle Insurance");
        Category healthInsurance = new Category(3, "Health Insurance");
        
        // Add subcategories (policies) to Life Insurance
        lifeInsurance.addSubCategory(new SubCategory(1, "Term Life Insurance", 1));
        lifeInsurance.addSubCategory(new SubCategory(2, "Whole Life Insurance", 1));
        
        // Add subcategories (policies) to Motor Vehicle Insurance
        motorInsurance.addSubCategory(new SubCategory(3, "Car Insurance", 2));
        motorInsurance.addSubCategory(new SubCategory(4, "Motorcycle Insurance", 2));
        motorInsurance.addSubCategory(new SubCategory(5, "Commercial Vehicle Insurance", 2));
        
        // Add subcategories (policies) to Health Insurance
        healthInsurance.addSubCategory(new SubCategory(6, "Dental Insurance", 3));
        healthInsurance.addSubCategory(new SubCategory(7, "Medicaid Insurance", 3));
        healthInsurance.addSubCategory(new SubCategory(8, "Individual Insurance", 3));
        
        // Add all categories to the list
        categories.add(lifeInsurance);
        categories.add(motorInsurance);
        categories.add(healthInsurance);
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
    public void addCategory(String name) {
        int nextCategoryId = getNextCategoryId();
        Category newCategory = new Category(nextCategoryId, name);
        categories.add(newCategory);
    }
    
    @Override
    public void addSubCategory(int categoryId, String name, String description) {
        for (Category category : categories) {
            if (category.getId() == categoryId) {
                int nextPolicyId = getNextPolicyId();
                Policy newPolicy = new Policy(nextPolicyId, name, description, categoryId);
                policies.add(newPolicy);
                // Also add as a subcategory to the category
                category.addSubCategory(new SubCategory(newPolicy.getId(), newPolicy.getName(), categoryId));
                break;
            }
        }
    }
    
    @Override
    public void updateCategory(int categoryId, String newName) {
        boolean found = false;
        for (Category category : categories) {
            if (category.getId() == categoryId) {
                category.setName(newName);
                System.out.println("Category updated successfully.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Category with ID " + categoryId + " not found.");
        }
    }
    
    @Override
    public void updateSubCategory(int subCategoryId, String newName) {
        boolean found = false;
        for (Category category : categories) {
            for (SubCategory sub : category.getSubCategories()) {
                if (sub.getSubCategoryId() == subCategoryId) {
                    sub.setSubCategoryName(newName);
                    System.out.println("SubCategory updated successfully.");
                    found = true;
                    break;
                }
            }
            if (found) break;
        }
        if (!found) {
            System.out.println("SubCategory with ID " + subCategoryId + " not found.");
        }
    }
    
    @Override
    public void updatePolicy(int policyId, String newPolicyName, String newPolicyDetails) {
        boolean found = false;
        for (Policy policy : policies) {
            if (policy.getId() == policyId) {
                policy.setName(newPolicyName);
                policy.setDescription(newPolicyDetails);
                System.out.println("Policy updated successfully.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Policy with ID " + policyId + " not found.");
        }
    }
    
    // Helper method to get the next category ID
    private int getNextCategoryId() {
        return categories.size() + 1;
    }
    
    // Helper method to get the next policy ID
    private int getNextPolicyId() {
        return policies.size() + 1;
    }
}
