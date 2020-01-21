package org.ecabs.bookings.application.rest;

import org.ecabs.bookings.domain.messagebroker.Booking;
import org.ecabs.bookings.infrastructure.db.AuditDAO;
import org.ecabs.bookings.infrastructure.db.BookingDAO;
import org.ecabs.bookings.infrastructure.messagebroker.BookingProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QueueController {

    @Autowired
    BookingProducerService producer;

    @Autowired
    BookingDAO booking;


    @GetMapping("/ecabs/booking/")
    public List<Booking> getAllBookings(){
        return booking.getBooking();
    }

    @GetMapping("/ecabs/booking/{id}")
    public List<Booking> getBookingsByID(@PathVariable String id){
        return booking.getBookingById(id);
    }

    @PostMapping("/ecabs/booking/add")
    public Booking addBooking(@RequestBody Booking booking){
        producer.sendAdd(booking);
        return booking;
    }

    @PatchMapping("/ecabs/booking/edit")
    public Booking editBooking(@RequestBody Booking booking){
        producer.sendEdit(booking);
        return booking;
    }

    @DeleteMapping("/ecabs/booking/delete")
    public Booking deleteBooking(@RequestBody Booking booking){
        producer.sendDelete(booking);
        return booking;
    }

}
