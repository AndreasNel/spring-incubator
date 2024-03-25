package entelect.training.incubator.spring.booking.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingSearchRequest {
    private Integer customerId;
    private Integer referenceNumber;
}
