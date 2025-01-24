package Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "auditlogs")
public class Auditlog {
    @Id
    @Column(name = "log_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private Model.User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "shared_account_id", nullable = false, referencedColumnName = "shared_account_id")
    private Model.Useraccountaccess sharedAccount;

    @Lob
    @Column(name = "action", nullable = false)
    private String action;

    @Column(name = "record_id", nullable = false)
    private Long recordId;

    @Column(name = "table_name", nullable = false)
    private String tableName;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "access_time")
    private Instant accessTime;

}