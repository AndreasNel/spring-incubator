package entelect.training.incubator.spring.booking.repository;

import entelect.training.incubator.spring.booking.config.ApplicationConfig;
import entelect.training.incubator.spring.booking.model.Customer;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    private  RestClient restClient;
    private final ApplicationConfig applicationConfig;

    public CustomerRepositoryImpl(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    @PostConstruct
    public void init() {
        String baseUrl = applicationConfig.getCustomerService().baseUrl();
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION,
                        getCredentials())
                .build();
    }

    private String getCredentials() {
        String username = applicationConfig.getCustomerService().username();
        String password = applicationConfig.getCustomerService().password();
        String value = username + ':' + password;
        return "Basic " + Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public Customer getCustomer(Integer customerId) {
        return restClient.get().uri("customers/" + customerId.toString()).retrieve().toEntity(Customer.class).getBody();
    }
}
