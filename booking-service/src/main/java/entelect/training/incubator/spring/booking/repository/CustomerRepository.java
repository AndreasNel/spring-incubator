package entelect.training.incubator.spring.booking.repository;

import entelect.training.incubator.spring.booking.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository {
    public Customer getCustomer(Integer customerId);
}
