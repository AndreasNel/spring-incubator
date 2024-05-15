package entelect.training.incubator.spring.booking.service;

import entelect.training.incubator.spring.booking.model.BookingMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessagingService {

    private final JmsTemplate jmsTemplate;

    public MessagingService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(BookingMessage message) {
        jmsTemplate.convertAndSend("notifications.send", message);
    }
}
