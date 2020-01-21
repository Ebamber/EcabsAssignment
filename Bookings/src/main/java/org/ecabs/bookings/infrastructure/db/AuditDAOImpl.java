package org.ecabs.bookings.infrastructure.db;

import org.ecabs.bookings.domain.db.AuditEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class AuditDAOImpl implements AuditDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void audit(String event) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(new AuditEntity(event));
        transaction.commit();
        session.close();
    }

}
