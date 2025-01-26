package com.example.ExpenseManagementApp.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "useraccountaccess")
public class Useraccountaccess {
    @EmbeddedId
    private UseraccountaccessId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @MapsId("sharedAccountId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "shared_account_id", nullable = false)
    private Account sharedAccount;

    @ColumnDefault("'ADMIN'")
    @Lob
    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "created_by", nullable = false)
    private Long createdBy;

}