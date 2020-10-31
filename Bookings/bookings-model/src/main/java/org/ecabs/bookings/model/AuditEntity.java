package org.ecabs.bookings.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="audits")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditEntity implements Serializable {

    @Id
    @Column(name="transactionId")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name="bookingMessage")
    private String bookingMessage;
}
