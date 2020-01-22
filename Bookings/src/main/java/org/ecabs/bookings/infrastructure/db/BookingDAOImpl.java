package org.ecabs.bookings.infrastructure.db;

import org.ecabs.bookings.domain.db.BookingEntity;
import org.ecabs.bookings.domain.messagebroker.Booking;
import org.ecabs.bookings.domain.messagebroker.TripWaypoint;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookingDAOImpl implements BookingDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private WaypointDAO waypointDAO;

    @Override
    public List<Booking> getBooking() {
        Session session = this.sessionFactory.openSession();
        List<Booking> bookingList = session.createQuery("SELECT b FROM Booking b", Booking.class).getResultList();
        session.close();

        return bookingList;
    }

    @Override
    public List<Booking> getBookingById(String bookingId) {
        Session session = this.sessionFactory.openSession();
        List<Booking> bookingList = session.createQuery("from Booking where bookingId = :bookingId").setParameter("bookingId", UUID.fromString(bookingId)).list();
        bookingList.forEach(booking -> booking.setTripWayPoints(waypointDAO.getWaypointsByBooking(booking)));
        session.close();

        return bookingList;
    }

    @Override
    public void insert(Booking booking) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(booking);
        booking.getTripWayPoints().forEach(waypoint -> session.persist(waypoint));
        transaction.commit();
        session.close();

    }

    @Override
    public void delete(Booking booking) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(booking);
        booking.getTripWayPoints().forEach(waypoint -> session.delete(waypoint));
        transaction.commit();
        session.close();

    }

    @Override
    public void update(Booking booking) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(booking);
        booking.getTripWayPoints().forEach(waypoint -> session.update(waypoint));
        transaction.commit();
        session.close();

    }
}
