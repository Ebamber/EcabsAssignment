package org.ecabs.bookings.domain.db;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name="audits")
@ToString
@RequiredArgsConstructor
public class AuditEntity {

    public AuditEntity(String bookingMessage){
        this.bookingMessage = bookingMessage;
    }

    @Id
    @Column(name="transactionId")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="bookingMessage")
    private String bookingMessage;
}
