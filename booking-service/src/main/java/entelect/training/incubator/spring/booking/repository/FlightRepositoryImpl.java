package entelect.training.incubator.spring.booking.repository;

import entelect.training.incubator.spring.booking.config.ApplicationConfig;
import entelect.training.incubator.spring.booking.model.Flight;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Repository
public class FlightRepositoryImpl implements FlightRepository {
    private final ApplicationConfig applicationConfig;
    private RestClient restClient;

    public FlightRepositoryImpl(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    @PostConstruct
    public void init() {
        String baseUrl = applicationConfig.getFlightService().baseUrl();
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION,
                        getCredentials())
                .build();
    }

    private String getCredentials() {
        String username = applicationConfig.getFlightService().username();
        String password = applicationConfig.getFlightService().password();
        String value = username + ':' + password;
        return "Basic " + Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public Flight getFlight(Integer flightId) {
        return restClient.get().uri("flights/" + flightId.toString()).retrieve().toEntity(Flight.class).getBody();
    }
}
