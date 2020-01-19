package org.ecabs.bookings.application;

import org.ecabs.bookings.domain.Booking;
import org.ecabs.bookings.infrastructure.db.HibernateWrapper;
import org.ecabs.bookings.infrastructure.messagebroker.BookingConsumerService;
import org.ecabs.bookings.infrastructure.messagebroker.BookingProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QueueController {

    @Autowired
    BookingProducerService producer;

    @Autowired
    HibernateWrapper dbService;

    @GetMapping("/ecabs/booking/")
    public List<Booking> getAllBookings(){
        return booking;
    }

    @GetMapping("/ecabs/booking/{id}")
    public List<Booking> getBookingsByID(@PathVariable int id){
        return booking;
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
