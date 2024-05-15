package entelect.training.incubator.spring.notification.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Notification {
    private String phoneNumber;
    private String message;
}
