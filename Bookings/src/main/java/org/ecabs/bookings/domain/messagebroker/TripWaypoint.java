package org.ecabs.bookings.domain.messagebroker;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.ecabs.bookings.infrastructure.CustomInstantDeserializer;
import org.ecabs.bookings.infrastructure.CustomInstantSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="waypoints")
public class TripWaypoint implements Serializable {

    @Id
    @Getter @Setter
    @Column(name="waypointId")
    private UUID waypointId;

    @JsonBackReference
    @ManyToOne
    @Getter @Setter
    @JoinColumn(name="bookingId", nullable=false)
    private Booking booking;

    @Column(name="lastStop")
    @Getter @Setter
    private Boolean lastStop;

    @Column(name="locality")
    @Getter @Setter
    private String locality;

    @Column(name="latitude")
    @Getter @Setter
    private Double lat;

    @Column(name="longitude")
    @Getter @Setter
    private Double lng;

    @Column(name="timestamp")
    @Getter @Setter
    @JsonSerialize(using = CustomInstantSerializer.class)
    @JsonDeserialize(using = CustomInstantDeserializer.class)
    private Instant tripWayPointTimestamp;


}
