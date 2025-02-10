// Policy.java
package com.pms.model;

public class Policy {
    private int id;
    private String name;
    private String description;

    public Policy(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
}
