package africa.smicolon.customer.model.repositories;

import africa.smicolon.customer.model.data.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {

    Optional<Billing> findByCustomerId(Long customerId);
}
