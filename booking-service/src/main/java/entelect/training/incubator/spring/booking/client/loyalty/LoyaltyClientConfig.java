package entelect.training.incubator.spring.booking.client.loyalty;

import entelect.training.incubator.spring.booking.config.ApplicationConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class LoyaltyClientConfig {
    private final ApplicationConfig applicationConfig;

    public LoyaltyClientConfig(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("entelect.training.incubator.spring.booking.client.loyalty.generated");
        return marshaller;
    }

    @Bean
    public LoyaltyClient loyaltyClient(Jaxb2Marshaller marshaller) {
        LoyaltyClient loyaltyClient = new LoyaltyClient();
        loyaltyClient.setDefaultUri(applicationConfig.getLoyaltyService().baseUrl() + "/ws");
        loyaltyClient.setMarshaller(marshaller);
        loyaltyClient.setUnmarshaller(marshaller);
        return loyaltyClient;
    }
}
