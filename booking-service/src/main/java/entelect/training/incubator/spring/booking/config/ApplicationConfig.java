package entelect.training.incubator.spring.booking.config;

import entelect.training.incubator.spring.booking.model.ServiceConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "booking")
@Data
public class ApplicationConfig {
    private ServiceConfig flightService;
    private ServiceConfig customerService;
}
