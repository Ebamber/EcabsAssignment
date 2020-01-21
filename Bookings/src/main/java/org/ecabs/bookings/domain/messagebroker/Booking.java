package org.ecabs.bookings.domain.messagebroker;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
public class Booking implements Serializable {

    private UUID bookingId;
    private String passengerName;
    private String passengerContactNumber;
    private OffsetDateTime pickupTime;
    private boolean asap;
    private int waitingTime;
    private int noOfPassengers;
    private BigDecimal price;
    private int rating;
    private Instant createdOn;
    private Instant lastModifiedOn;
    private List<TripWaypoint> tripWayPoints;

}