package org.ecabs.bookings.infrastructure.messagebroker;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookingConsumerService {
    @RabbitListener(queues = "${ecabs.message.booking.add}")
    public void BookingAddConsumer(String in) {
        System.out.println("Message read from myQueue : " + in);
    }

    @RabbitListener(queues = "${ecabs.message.booking.delete}")
    public void BookingDeleteConsumer(String in) {
        System.out.println("Message read from myQueue : " + in);
    }

    @RabbitListener(queues = "${ecabs.message.booking.edit}")
    public void BookingEditConsumer(String in) {
        System.out.println("Message read from myQueue : " + in);
    }

    @RabbitListener(queues = "${ecabs.message.audit}")
    public void MessageAuditConsumer(String in) {
        System.out.println("Message read from myQueue : " + in);
    }

}
