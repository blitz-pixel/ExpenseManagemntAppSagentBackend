package com.example.ExpenseManagementApp.DTO;

import com.example.ExpenseManagementApp.Model.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import com.example.ExpenseManagementApp.Model.Category;
import jakarta.annotation.Nullable;

public class CategoryDTO {

    @Nullable
    private Long accountId;
    @Nullable
    private Long userId;
    @Nullable
    @JsonProperty("SubCategoryName")
    private String SubCategoryName;
    @JsonProperty("ParentCategoryName")
    private String ParentCategoryName;
    private Category.CatType type;

    public CategoryDTO(@Nullable Long accountId, @Nullable Long userId, @Nullable String SubCategoryName, @Nullable String ParentCategoryName, Category.CatType type) {
        this.accountId = accountId;
        this.userId = userId;
        this.SubCategoryName = SubCategoryName;
        this.ParentCategoryName = ParentCategoryName;
        this.type = type;
    }

    public CategoryDTO(){
    }

    public CategoryDTO(Category category){
        if (category.getAccount() != null)this.accountId = category.getAccount().getAccount_id();
        if (category.getUser() != null)this.userId = category.getUser().getUser_id();
        if(category.getParent() != null){
            this.SubCategoryName = category.getName();
            this.ParentCategoryName = category.getParent().getName();
        }else{
            this.ParentCategoryName = category.getName();
            this.SubCategoryName = "";
        }
        this.type = category.getType();
    }

    @Nullable
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(@Nullable Long accountId) {
        this.accountId = accountId;
    }

    @Nullable
    public Long getUserId() {
        return userId;
    }

    public void setUserId(@Nullable Long userId) {
        this.userId = userId;
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