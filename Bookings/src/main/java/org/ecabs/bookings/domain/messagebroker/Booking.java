package org.ecabs.bookings.domain.messagebroker;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Entity
@Table(name="bookings")
public class Booking implements Serializable {

    @Id
    @Column(name="bookingId")
    private UUID bookingId;

    @Column(name="passengerName")
    private String passengerName;

    @Column(name="passengerContactNumber")
    private String passengerContactNumber;

    @Column(name="pickupTime")
    private OffsetDateTime pickupTime;

    @Column(name="asap")
    private Boolean asap;

    @Column(name="waitingTime")
    private Integer waitingTime;

    @Column(name="noOfPassengers")
    private Integer noOfPassengers;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="rating")
    private Integer rating;

    @Column(name="createdOn")
    private Instant createdOn;

    @Column(name="lastModifiedOn")
    private Instant lastModifiedOn;

    @OneToMany(mappedBy = "booking")
    private List<TripWaypoint> tripWayPoints;

}