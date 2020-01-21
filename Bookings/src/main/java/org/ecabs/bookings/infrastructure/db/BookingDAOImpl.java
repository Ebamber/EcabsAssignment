package org.ecabs.bookings.infrastructure.db;

import org.ecabs.bookings.domain.db.BookingEntity;
import org.ecabs.bookings.domain.messagebroker.Booking;
import org.ecabs.bookings.domain.messagebroker.TripWaypoint;
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
        List<Booking> bookingList = session.createQuery("from bookings").list();
        session.close();

        //region deprecated, used to map DB to MQ objects
        /*
        List<BookingEntity> bookingResult = session.createQuery("from bookings").list();
        session.close();

        List<Booking> bookingList = bookingResult
                .parallelStream()
                .map(BookingEntity::toBookingEvent)
                .collect(Collectors.toList());

        bookingList.forEach(booking -> booking.setTripWayPoints(waypointDAO.getWaypointsByBooking(booking)));
        */
        //endregion

        return bookingList;
    }

    @Override
    public List<Booking> getBookingById(String bookingId) {
        Session session = this.sessionFactory.openSession();
        List<Booking> bookingList = session.createQuery("from bookings where bookingId = :bookingId").setParameter("bookingId",bookingId).list();
        bookingList.forEach(booking -> booking.setTripWayPoints(waypointDAO.getWaypointsByBooking(booking)));
        session.close();

        //region deprecated, used to map DB to MQ objects
        /*
        List<BookingEntity> bookingResult = session.createQuery("from bookings where bookingId = :bookingId").setParameter("bookingId",bookingId).list();
        session.close();

        List<Booking> bookingList = bookingResult
                                        .parallelStream()
                                        .map(BookingEntity::toBookingEvent)
                                        .collect(Collectors.toList());

        bookingList.forEach(booking -> booking.setTripWayPoints(waypointDAO.getWaypointsByBooking(booking)));
        */
        //endregion

        return bookingList;
    }

    @Override
    public void insert(Booking booking) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(booking);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Booking booking) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(booking);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Booking booking) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(booking);
        transaction.commit();
        session.close();
    }
}
