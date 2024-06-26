package entelect.training.incubator.spring.booking.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Customer {
    private Integer customerId;
    private String passportNumber;
    private String phoneNumber;
    private String firstName;
    private String lastName;
}
