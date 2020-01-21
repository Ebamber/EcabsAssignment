package org.ecabs.bookings.domain.db;

import lombok.Data;
import lombok.ToString;
import org.ecabs.bookings.domain.messagebroker.Booking;
import org.ecabs.bookings.domain.messagebroker.TripWaypoint;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Data
@Entity
@Table(name="bookings")
@ToString
public class BookingEntity {
    @Id
    @Column(name="bookingId")
    private String bookingId;

    @Column(name="passengerName")
    private String passengerName;

    @Column(name="passengerContactNumber")
    private String passengerContactNumber;

    @Column(name="pickupTime")
    private OffsetDateTime pickupTime;

    @Column(name="asap")
    private boolean asap;

    @Column(name="waitingTime")
    private int waitingTime;

    @Column(name="noOfPassengers")
    private int noOfPassengers;

    @Column(name="price")
    private double price;

    @Column(name="rating")
    private int rating;

    @Column(name="createdOn")
    private Instant createdOn;

    @Column(name="lastModifiedOn")
    private Instant lastModifiedOn;

    public Booking toBookingEvent(){
        return new Booking(UUID.fromString(bookingId),
                passengerName,
                passengerContactNumber,
                pickupTime,
                asap,
                waitingTime,
                noOfPassengers,
                BigDecimal.valueOf(price),
                rating,
                createdOn,
                lastModifiedOn,
                new ArrayList<TripWaypoint>());
    }

    public BookingEntity fromBookingEvent(Booking booking){
        this.bookingId = booking.getBookingId().toString();
        this.passengerName = booking.getPassengerName();
        this.passengerContactNumber = booking.getPassengerContactNumber();
        this.pickupTime = booking.getPickupTime();
        this.asap = booking.getAsap();
        this.waitingTime = booking.getWaitingTime();
        this.noOfPassengers = booking.getNoOfPassengers();
        this.price = booking.getPrice().doubleValue();
        this.rating = booking.getRating();
        this.createdOn = booking.getCreatedOn();
        this.lastModifiedOn = booking.getLastModifiedOn();
        return this;
    }

}
