package com.example.ExpenseManagementApp.DTO;

import com.example.ExpenseManagementApp.Model.Category;
import com.example.ExpenseManagementApp.Model.Transaction;

import javax.xml.crypto.Data;
import java.util.Date;

public class ExpenseDTO {
    private Long id;
    private String description;
    private Date date;
    private String categoryName;
    private Double amount;


    public ExpenseDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.description = transaction.getDescription();
        this.date = Date.from(transaction.getDate());
        this.categoryName = transaction.getCategory().getName();
        this.amount = transaction.getAmount().doubleValue();
    }
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
