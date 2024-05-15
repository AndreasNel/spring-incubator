package entelect.training.incubator.spring.booking.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Flight {
    private Integer id;
    private BigDecimal seatCost;
    private String flightNumber;
    private LocalDateTime departureTime;
}
