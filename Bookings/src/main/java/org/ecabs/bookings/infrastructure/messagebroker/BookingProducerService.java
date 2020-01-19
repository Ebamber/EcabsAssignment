package org.ecabs.bookings.infrastructure.messagebroker;

import org.ecabs.bookings.domain.Booking;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingProducerService {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private Exchange messageExchange;

    public void sendAdd(Booking booking) {
        this.rabbitTemplate.convertAndSend(messageExchange.getName(), "ADD", booking);
    }

    public void sendEdit(Booking booking) {
        this.rabbitTemplate.convertAndSend(messageExchange.getName(),"EDIT", booking);
    }

    public void sendDelete(Booking booking) {
        this.rabbitTemplate.convertAndSend(messageExchange.getName(), "DELETE", booking);
    }

    public void sendAudit(Booking booking) {
        this.rabbitTemplate.convertAndSend(messageExchange.getName(), booking);
    }

}
