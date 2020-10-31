package org.ecabs.bookings.infrastructure.service;

import lombok.RequiredArgsConstructor;
import org.ecabs.bookings.infrastructure.db.AuditRepository;
import org.ecabs.bookings.infrastructure.db.BookingRepository;
import org.ecabs.bookings.infrastructure.db.WaypointRepository;
import org.ecabs.bookings.model.AuditEntity;
import org.ecabs.bookings.model.Booking;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final AuditRepository auditRepo;
    private final BookingRepository bookingRepo;
    private final WaypointRepository waypointRepo;

    public void updateBooking(Booking bookingEntity) {
        bookingRepo.save(bookingEntity);
        bookingEntity.getTripWayPoints().forEach(waypoint -> {
            waypoint.setBooking(bookingEntity);
            waypointRepo.save(waypoint);
        });
    }

    public void deleteBooking(Booking bookingEntity) {
        bookingRepo.delete(bookingEntity);
    }

    public void auditBooking(Booking bookingEntity) {
        AuditEntity auditEntity = AuditEntity.builder()
                .bookingMessage(bookingEntity.toString())
                .build();
        auditRepo.save(auditEntity);
    }
}
