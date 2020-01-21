package org.ecabs.bookings.infrastructure.db;

import org.ecabs.bookings.domain.db.WaypointEntity;
import org.ecabs.bookings.domain.messagebroker.Booking;
import org.ecabs.bookings.domain.messagebroker.TripWaypoint;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class WaypointDAOImpl implements WaypointDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TripWaypoint> getWaypointsByBooking(Booking booking) {
        Session session = this.sessionFactory.openSession();
        List<TripWaypoint> waypointList = session.createQuery("from waypoints where bookingId = :bookingId").setParameter("bookingId", booking.getBookingId().toString()).list();
        session.close();

        //region deprecated, used to map DB to MQ objects
        /*
        List<WaypointEntity> waypointResult = session.createQuery("from waypoints where bookingId = :bookingId").setParameter("bookingId",booking.getBookingId().toString()).list();
        session.close();

        List<TripWaypoint> waypointList = waypointResult
                                            .parallelStream()
                                            .map(WaypointEntity::toTripWaypoint)
                                            .collect(Collectors.toList());

        waypointList.forEach(waypoint -> waypoint.setBooking(booking));
         */
        //endregion

        return waypointList;
    }

    @Override
    public void insert(TripWaypoint waypoint) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(waypoint);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(TripWaypoint waypoint) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(waypoint);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(TripWaypoint waypoint) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(waypoint);
        transaction.commit();
        session.close();
    }
}
