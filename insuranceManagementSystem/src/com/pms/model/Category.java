package com.pms.model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private int id;
    private String name;
    private List<SubCategory> subCategories;
    
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
        this.subCategories = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public List<SubCategory> getSubCategories() {
        return subCategories;
    }
    
    public void addSubCategory(SubCategory subCategory) {
        subCategories.add(subCategory);
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
