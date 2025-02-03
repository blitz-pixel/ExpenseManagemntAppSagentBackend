package com.example.ExpenseManagementApp.DTO;

import com.example.ExpenseManagementApp.Model.Category;
import jakarta.annotation.Nullable;
import com.example.ExpenseManagementApp.Model.Category;
import jakarta.annotation.Nullable;

public class CategoryDTO {

    @Nullable
    private Long accountId;
    @Nullable
    private Long user_id;
    @Nullable
    private String SubCategoryName;

    private String ParentCategoryName;
    private Category.CatType type;

    public CategoryDTO(@Nullable Long accountId, @Nullable Long user_id, String SubCategoryName, @Nullable String ParentCategoryName, Category.CatType type) {
        this.accountId = accountId;
        this.user_id = user_id;
        this.SubCategoryName = SubCategoryName;
        this.ParentCategoryName = ParentCategoryName;
        this.type = type;
    }

    @Nullable
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(@Nullable Long accountId) {
        this.accountId = accountId;
    }

    @Nullable
    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(@Nullable Long user_id) {
        this.user_id = user_id;
    }

    public String getSubCategoryName() {
        return SubCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.SubCategoryName = subCategoryName;
    }

    @Nullable
    public String getParentCategoryName() {
        return ParentCategoryName;
    }

    public void setParentCategoryName(@Nullable String parentId) {
        this.ParentCategoryName = parentId;
    }

    public Category.CatType getType() {
        return type;
    }

    public void setType(Category.CatType type) {
        this.type = type;
    }
}