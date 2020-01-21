package org.ecabs.bookings.domain.messagebroker;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Entity
@Table(name="waypoints")
public class TripWaypoint implements Serializable {

    @Id
    @Column(name="waypointId")
    private UUID waypointId;

    @ManyToOne
    @JoinColumn(name="bookingId", nullable=false)
    private Booking booking;

    @Column(name="lastStop")
    private Boolean lastStop;

    @Column(name="locality")
    private String locality;

    @Column(name="latitude")
    private Double lat;

    @Column(name="longitude")
    private Double lng;

    @Column(name="timestamp")
    private Instant tripWayPointTimestamp;


}
