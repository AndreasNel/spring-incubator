package entelect.training.incubator.spring.booking.repository;

import entelect.training.incubator.spring.booking.model.Flight;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

@Repository
public interface FlightRepository {
    public Flight getFlight(Integer flightId);
}
