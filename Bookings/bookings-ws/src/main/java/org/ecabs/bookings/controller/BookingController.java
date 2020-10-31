package org.ecabs.bookings.controller;

import lombok.RequiredArgsConstructor;
import org.ecabs.bookings.model.Booking;
import org.springframework.web.bind.annotation.*;
import org.ecabs.bookings.infrastructure.db.BookingRepository;
import org.ecabs.bookings.infrastructure.messagebroker.BookingProducerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingProducerService producer;
    private final BookingRepository bookingRepository;

    @GetMapping
    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public List<Booking> getBookingsByID(@PathVariable UUID id){
        return bookingRepository.findAllByBookingId(id);
    }

    @PostMapping
    public Booking addBooking(@RequestBody Booking booking){
        producer.sendAdd(booking);
        return booking;
    }

    @PatchMapping
    public Booking editBooking(@RequestBody Booking booking){
        producer.sendEdit(booking);
        return booking;
    }

    @DeleteMapping
    public List<Booking> deleteBooking(@RequestBody UUID bookingId){
        List<Booking> bookingList = bookingRepository.findAllByBookingId(bookingId);
        bookingList.forEach(producer::sendDelete);
        return bookingList;
    }

}
