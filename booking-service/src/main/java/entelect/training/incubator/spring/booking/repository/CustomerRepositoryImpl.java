package entelect.training.incubator.spring.booking.repository;

import entelect.training.incubator.spring.booking.model.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    private final RestClient restClient;

    public CustomerRepositoryImpl() {
        this.restClient = RestClient.create("http://localhost:8201");
    }

    @Override
    public Customer getCustomer(Integer customerId) {
        return restClient.get().uri(customerId.toString()).retrieve().toEntity(Customer.class).getBody();
    }
}
