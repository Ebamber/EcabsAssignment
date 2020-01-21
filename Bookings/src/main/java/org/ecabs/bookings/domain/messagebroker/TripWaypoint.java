package org.ecabs.bookings.domain.messagebroker;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer;
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

    @JsonBackReference
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
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss.SSSSSSx")
    @JsonProperty("tripWaypointTimestamp")
    private Instant tripWayPointTimestamp;


}
