package org.ecabs.bookings.domain.db;

import lombok.Data;
import lombok.ToString;
import org.ecabs.bookings.domain.messagebroker.Booking;
import org.ecabs.bookings.domain.messagebroker.TripWaypoint;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name="waypoints")
@ToString
@Deprecated
public class WaypointEntity {

    @Id
    @Column(name="waypointId")
    private String waypointId;

    @Column(name="lastStop")
    private boolean lastStop;

    @Column(name="locality")
    private String locality;

    @Column(name="latitude")
    private double latitude;

    @Column(name="longitude")
    private double longitude;

    @Column(name="timestamp")
    private Instant timestamp;

    @Column(name="bookingId")
    private String bookingId;

    public TripWaypoint toTripWaypoint(){
        return new TripWaypoint(UUID.fromString(waypointId), new Booking(), lastStop, locality, latitude, longitude, timestamp);
    }

    public Object fromTripWaypoint(TripWaypoint waypoint) {
        this.waypointId = waypoint.getWaypointId().toString();
        this.lastStop = waypoint.getLastStop();
        this.locality = waypoint.getLocality();
        this.latitude = waypoint.getLat();
        this.longitude = waypoint.getLng();
        this.timestamp = waypoint.getTripWayPointTimestamp();
        this.bookingId = waypoint.getBooking().getBookingId().toString();
        return this;
    }
}
