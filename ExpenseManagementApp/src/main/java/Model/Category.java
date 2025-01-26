package Model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.springframework.data.jpa.repository.Meta;

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
}
