package org.ecabs.bookings.infrastructure.db;

public interface AuditDAO {
    public void audit(String event);
}
