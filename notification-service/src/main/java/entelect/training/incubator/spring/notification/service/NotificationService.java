package entelect.training.incubator.spring.notification.service;

import entelect.training.incubator.spring.notification.model.Notification;
import entelect.training.incubator.spring.notification.sms.client.SmsClient;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {
    private final SmsClient smsClient;

    public NotificationService(SmsClient smsClient) {
        this.smsClient = smsClient;
    }

    @JmsListener(destination = "notifications.send")
    public void sendNotification(Message<Notification> notification) {
        Notification payload = notification.getPayload();
        smsClient.sendSms(payload.getPhoneNumber(), payload.getMessage());
    }
}
