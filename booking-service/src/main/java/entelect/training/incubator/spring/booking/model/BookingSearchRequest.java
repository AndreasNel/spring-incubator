package entelect.training.incubator.spring.booking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class BookingSearchRequest {
    private Integer customerId;
    private Integer referenceNumber;
}
