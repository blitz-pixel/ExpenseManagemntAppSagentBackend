package com.example.ExpenseManagementApp.Model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {


    @Id
    @Column(name = "category_id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "scope", nullable = false)
    private Scope scope; // Enum type for 'USER' and 'ACCOUNT'


    @Column(name = "scope_id", nullable = false)
    private Long scope_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CatType type;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "parent_id")
    private Category parent;  // Self-referencing relationsh


    public Long getId() {
        return id;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public Long getScope_id() {
        return scope_id;
    }

    public void setScope_id(Long scope_id) {
        this.scope_id = scope_id;
    }

    public CatType getType() {
        return type;
    }

    public void setType(CatType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParent() {
        return parent;
    }

    public enum Scope {
        USER,
        ACCOUNT
    }

    public enum CatType {
        income,expense
    }


}
