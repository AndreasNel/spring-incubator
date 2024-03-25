package entelect.training.incubator.spring.booking.repository;

import entelect.training.incubator.spring.booking.model.Flight;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

@Repository
public class FlightRepositoryImpl implements FlightRepository {
    private final RestClient restClient;

    public FlightRepositoryImpl() {
        this.restClient = RestClient.create("http://localhost:8202");
    }

    @Override
    public Flight getFlight(Integer flightId) {
        return restClient.get().uri(flightId.toString()).retrieve().toEntity(Flight.class).getBody();
    }
}
