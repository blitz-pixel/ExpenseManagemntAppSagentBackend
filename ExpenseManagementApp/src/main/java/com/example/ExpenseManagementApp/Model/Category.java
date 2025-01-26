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

    @Lob
    @Column(name = "scope", nullable = false)
    private String scope;

    @Any()
    @AnyKeyJavaClass(Long.class)
    @Column(name = "scope_id")
    @AnyDiscriminatorValue(discriminator = "user", entity = User.class)
    @AnyDiscriminatorValue(discriminator = "account", entity = Account.class)
    @JoinColumn(name = "scope_id")
    private Object scope_id;
    @Lob
    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "parent_id")
    private Category parent;  // Self-referencing relationship


    public Long getId() {
        return id;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Object getScope_id() {
        return scope_id;
    }

    public void setScope_id(Object scope_id) {
        this.scope_id = scope_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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


}
