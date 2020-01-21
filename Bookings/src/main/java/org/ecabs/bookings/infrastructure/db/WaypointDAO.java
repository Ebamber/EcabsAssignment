package org.ecabs.bookings.infrastructure.db;

import org.ecabs.bookings.domain.messagebroker.Booking;
import org.ecabs.bookings.domain.messagebroker.TripWaypoint;

import java.util.List;

public interface WaypointDAO {

    public List<TripWaypoint> getWaypointsByBooking(Booking booking);
    public void insert(TripWaypoint waypoint);
    public void delete(TripWaypoint waypoint);
    public void update(TripWaypoint waypoint);

}
