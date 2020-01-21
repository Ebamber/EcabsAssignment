package org.ecabs.bookings.application.rest;

import org.ecabs.bookings.domain.messagebroker.Booking;
import org.ecabs.bookings.infrastructure.db.AuditDAO;
import org.ecabs.bookings.infrastructure.db.BookingDAO;
import org.ecabs.bookings.infrastructure.messagebroker.BookingProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingProducerService producer;

    @Autowired
    BookingDAO booking;


    @GetMapping
    public List<Booking> getAllBookings(){
        return booking.getBooking();
    }

    @GetMapping(path = "/{id}")
    public List<Booking> getBookingsByID(@PathVariable String id){
        return booking.getBookingById(id);
    }

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public Booking addBooking(@RequestBody Booking booking){
        producer.sendAdd(booking);
        return booking;
    }

    @PatchMapping(path = "/edit", consumes = "application/json", produces = "application/json")
    public Booking editBooking(@RequestBody Booking booking){
        producer.sendEdit(booking);
        return booking;
    }

    @DeleteMapping(path = "/delete", consumes = "application/json", produces = "application/json")
    public Booking deleteBooking(@RequestBody Booking booking){
        producer.sendDelete(booking);
        return booking;
    }

}
