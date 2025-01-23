package Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transaction_id;

}
