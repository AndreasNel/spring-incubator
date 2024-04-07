package entelect.training.incubator.spring.booking.client.loyalty;

import entelect.training.incubator.spring.booking.client.loyalty.generated.CaptureRewardsRequest;
import entelect.training.incubator.spring.booking.client.loyalty.generated.CaptureRewardsResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.math.BigDecimal;

public class LoyaltyClient extends WebServiceGatewaySupport {
    public CaptureRewardsResponse captureRewards(String passportNumber, BigDecimal amount) {
        CaptureRewardsRequest request = new CaptureRewardsRequest();
        request.setPassportNumber(passportNumber);
        request.setAmount(amount);

        return (CaptureRewardsResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
    }
}
