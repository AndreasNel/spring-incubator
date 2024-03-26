package entelect.training.incubator.spring.booking.repository;

import entelect.training.incubator.spring.booking.model.Flight;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository {
    Flight getFlight(Integer flightId);
}
