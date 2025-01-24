package Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class UseraccountaccessId implements java.io.Serializable {
    private static final long serialVersionUID = 4403079033395465427L;
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "shared_account_id", nullable = false)
    private Long sharedAccountId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UseraccountaccessId entity = (UseraccountaccessId) o;
        return Objects.equals(this.sharedAccountId, entity.sharedAccountId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sharedAccountId, userId);
    }

}