package africa.smicolon.customer.model.repositories;

import africa.smicolon.customer.model.data.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {

    Optional<Billing> findByCustomerId(Long customerId);


    @Query(nativeQuery = true, value = "select * from billing where customer_id =:customerId")
    List<Billing> findAllBillingsByCustomerId(Long customerId);
}
