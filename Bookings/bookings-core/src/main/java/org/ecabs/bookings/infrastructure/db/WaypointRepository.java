package org.ecabs.bookings.infrastructure.db;

import org.ecabs.bookings.model.TripWaypoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaypointRepository  extends JpaRepository<TripWaypoint, Long> {
}
