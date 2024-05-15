package entelect.training.incubator.spring.booking.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BookingMessage {
    private String phoneNumber;
    private String message;
}
