package org.ecabs.bookings.infrastructure.messagebroker;

import lombok.RequiredArgsConstructor;
import org.ecabs.bookings.model.Booking;
import org.ecabs.bookings.infrastructure.service.BookingService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BookingConsumerService {

    private final BookingService bookingService;

    @RabbitListener(queues = "${ecabs.message.booking.add}")
    public void BookingAddConsumer(Booking bookingEntity) {
        bookingService.updateBooking(bookingEntity);
    }

    @RabbitListener(queues = "${ecabs.message.booking.delete}")
    public void BookingDeleteConsumer(Booking bookingEntity) {
        bookingService.deleteBooking(bookingEntity);
    }

    @RabbitListener(queues = "${ecabs.message.booking.edit}")
    public void BookingEditConsumer(Booking bookingEntity) {
        bookingService.updateBooking(bookingEntity);
    }

    @RabbitListener(queues = "${ecabs.message.audit}")
    public void MessageAuditConsumer(Booking bookingEntity) {
        bookingService.auditBooking(bookingEntity);
    }


}
