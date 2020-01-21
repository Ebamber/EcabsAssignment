package org.ecabs.bookings.infrastructure.db;

import org.ecabs.bookings.domain.messagebroker.Booking;

import java.util.List;

public interface BookingDAO {
    public List<Booking> getBooking();
    public List<Booking> getBookingById(String bookingId);
    public void insert(Booking booking);
    public void delete(Booking booking);
    public void update(Booking booking);

}
