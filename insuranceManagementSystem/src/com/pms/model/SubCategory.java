package com.pms.model;
class SubCategory {
    private int subCategoryId;
    private String subCategoryName;
    private int categoryId;

    public SubCategory(int subCategoryId, String subCategoryName, int categoryId) {
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
        this.categoryId = categoryId;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }
}
