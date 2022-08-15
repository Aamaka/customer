package africa.smicolon.customer.model.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "accountNumber", nullable = false)
    private String accountNumber;

    @Column(name = "tariff", nullable = false)
    private String tariff;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Billing(Customer customer) {
        this.accountNumber = String.valueOf(UUID.randomUUID().getLeastSignificantBits()).substring(1,11)+"-01";
        this.customer = customer;
    }
}
