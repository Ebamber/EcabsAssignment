package org.ecabs.bookings.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.ecabs.bookings.utils.CustomInstantDeserializer;
import org.ecabs.bookings.utils.CustomInstantSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name="waypoints")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripWaypoint implements Serializable {

    @Id
    private UUID waypointId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bookings_bookingId")
    private Booking booking;
    private Boolean lastStop;
    private String locality;
    private Double lat;
    private Double lng;

    @JsonSerialize(using = CustomInstantSerializer.class)
    @JsonDeserialize(using = CustomInstantDeserializer.class)
    private Instant tripWayPointTimestamp;

}
