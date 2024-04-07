package entelect.training.incubator.spring.booking.config;

import entelect.training.incubator.spring.booking.model.RestServiceConfig;
import entelect.training.incubator.spring.booking.model.SoapServiceConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "booking")
@Data
public class ApplicationConfig {
    private RestServiceConfig flightService;
    private RestServiceConfig customerService;
    private SoapServiceConfig loyaltyService;
}
