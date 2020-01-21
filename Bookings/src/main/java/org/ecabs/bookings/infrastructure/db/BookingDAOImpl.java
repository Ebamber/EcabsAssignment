package org.ecabs.bookings.infrastructure.db;

import org.ecabs.bookings.domain.db.BookingEntity;
import org.ecabs.bookings.domain.messagebroker.Booking;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class BookingDAOImpl implements BookingDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private WaypointDAO waypointDAO;

    @Override
    public List<Booking> getBooking() {
        Session session = this.sessionFactory.openSession();
        List<BookingEntity> bookingResult = session.createQuery("from bookings").list();
        session.close();

        List<Booking> bookingList = bookingResult
                .parallelStream()
                .map(BookingEntity::toBookingEvent)
                .collect(Collectors.toList());

        bookingList.forEach(booking -> booking.setTripWayPoints(waypointDAO.getWaypointsByBooking(booking)));
        return bookingList;
    }

    @Override
    public List<Booking> getBookingById(String bookingId) {
        Session session = this.sessionFactory.openSession();
        List<BookingEntity> bookingResult = session.createQuery("from bookings where bookingId = :bookingId").setParameter("bookingId",bookingId).list();
        session.close();

        List<Booking> bookingList = bookingResult
                                        .parallelStream()
                                        .map(BookingEntity::toBookingEvent)
                                        .collect(Collectors.toList());

        bookingList.forEach(booking -> booking.setTripWayPoints(waypointDAO.getWaypointsByBooking(booking)));
        return bookingList;
    }

    @Override
    public void insert(Booking booking) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(new BookingEntity().fromBookingEvent(booking));
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Booking booking) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(new BookingEntity().fromBookingEvent(booking));
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Booking booking) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(new BookingEntity().fromBookingEvent(booking));
        transaction.commit();
        session.close();
    }
}
