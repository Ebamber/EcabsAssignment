package org.ecabs.bookings.infrastructure.messagebroker;

import org.ecabs.bookings.domain.messagebroker.Booking;
import org.ecabs.bookings.infrastructure.db.AuditDAO;
import org.ecabs.bookings.infrastructure.db.BookingDAO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingConsumerService {

    @Autowired
    AuditDAO audit;

    @Autowired
    BookingDAO booking;

    @RabbitListener(queues = "${ecabs.message.booking.add}")
    public void BookingAddConsumer(Booking bookingEntity) {
        System.out.println("EVENT CAME IN THE ADD QUEUE");
        System.out.println(bookingEntity.toString());
        booking.insert(bookingEntity);
    }

    @RabbitListener(queues = "${ecabs.message.booking.delete}")
    public void BookingDeleteConsumer(Booking bookingEntity) {
        System.out.println("EVENT CAME IN THE DELETE QUEUE");
        System.out.println(bookingEntity.toString());
        booking.delete(bookingEntity);
    }

    @RabbitListener(queues = "${ecabs.message.booking.edit}")
    public void BookingEditConsumer(Booking bookingEntity) {
        System.out.println("EVENT CAME IN THE EDIT QUEUE");
        System.out.println(bookingEntity.toString());
        booking.update(bookingEntity);
    }

    @RabbitListener(queues = "${ecabs.message.audit}")
    public void MessageAuditConsumer(Booking bookingEntity) {
        System.out.println("EVENT CAME IN THE AUDIT QUEUE");
        System.out.println(bookingEntity.toString());
        audit.audit(bookingEntity.toString());
    }

}
