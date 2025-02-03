package com.example.ExpenseManagementApp.DTO;

import com.example.ExpenseManagementApp.Model.Transaction;

import java.util.Date;

public class RevenueResponseDTO {
    private Long accountId; // For Testing
    private String description;
    private Date date;
    private String ParentcategoryName;
    private String subCategoryName;
    private Double amount;

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }



    public RevenueResponseDTO(Transaction transaction) {
        this.accountId = transaction.getAccount().getAccount_id();
        this.description = transaction.getDescription();
        this.date = Date.from(transaction.getDate());
        this.amount = transaction.getAmount().doubleValue();
        if (transaction.getCategory().getParent() != null) {
            this.subCategoryName = transaction.getCategory().getName();
            this.ParentcategoryName = transaction.getCategory().getParent().getName();
        } else {
            this.ParentcategoryName = transaction.getCategory().getName();
            this.subCategoryName = "";
        }
    }
    // Getters and Setters
    public Long getId() {
        return accountId;
    }

    public void setId(Long id) {
        this.accountId = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getParentcategoryName() {
        return ParentcategoryName;
    }

    public void setParentcategoryName(String categoryName) {
        this.ParentcategoryName = categoryName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
