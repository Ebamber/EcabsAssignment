package org.ecabs.bookings.infrastructure.messagebroker;

import org.ecabs.bookings.domain.messagebroker.Booking;
import org.ecabs.bookings.infrastructure.db.AuditDAO;
import org.ecabs.bookings.infrastructure.db.BookingDAO;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingConsumerService {

    @Autowired
    AuditDAO audit;

    @Autowired
    BookingDAO booking;

    @RabbitListener(ackMode = "AUTO", queues = "${ecabs.message.booking.add}")
    public void BookingAddConsumer(Booking bookingEntity) {
        booking.insert(bookingEntity);
    }

    @RabbitListener(ackMode = "AUTO", queues = "${ecabs.message.booking.delete}")
    public void BookingDeleteConsumer(Booking bookingEntity) {
        booking.delete(bookingEntity);
    }

    @RabbitListener(ackMode = "AUTO", queues = "${ecabs.message.booking.edit}")
    public void BookingEditConsumer(Booking bookingEntity) {
        booking.update(bookingEntity);
    }

    @RabbitListener(ackMode = "AUTO", queues = "${ecabs.message.audit}")
    public void MessageAuditConsumer(Booking bookingEntity) {
        audit.audit(bookingEntity.toString());
    }

}
