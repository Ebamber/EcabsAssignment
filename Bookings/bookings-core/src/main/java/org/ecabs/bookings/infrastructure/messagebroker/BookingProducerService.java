package org.ecabs.bookings.infrastructure.messagebroker;

import lombok.RequiredArgsConstructor;
import org.ecabs.bookings.model.Booking;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Exchange;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingProducerService {

    private final AmqpTemplate rabbitTemplate;
    private final Exchange bookingExchange;

    public void sendAdd(Booking booking) {
        this.rabbitTemplate.convertAndSend(bookingExchange.getName(), "ADD", booking);
    }

    public void sendEdit(Booking booking) {
        this.rabbitTemplate.convertAndSend(bookingExchange.getName(),"EDIT", booking);
    }

    public void sendDelete(Booking booking) {
        this.rabbitTemplate.convertAndSend(bookingExchange.getName(), "DELETE", booking);
    }

    public void sendAudit(Booking booking) {
        this.rabbitTemplate.convertAndSend(bookingExchange.getName(), booking);
    }

}
