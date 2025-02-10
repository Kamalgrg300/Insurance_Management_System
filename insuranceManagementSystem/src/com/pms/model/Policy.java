package com.pms.model;

public class Policy {
    private int id;
    private String name;
    private String description;
    private int categoryId;
    
    public Policy(int id, String name, String description, int categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public int getCategoryId() {
        return categoryId;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
