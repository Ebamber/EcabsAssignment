package org.ecabs.bookings.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.ecabs.bookings.utils.CustomInstantDeserializer;
import org.ecabs.bookings.utils.CustomInstantSerializer;
import org.ecabs.bookings.utils.CustomOffsetDateTimeDeserializer;
import org.ecabs.bookings.utils.CustomOffsetDateTimeSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking implements Serializable {

    @Id
    private UUID bookingId;

    private String passengerName;
    private String passengerContactNumber;
    private Boolean asap;
    private Integer waitingTime;
    private Integer noOfPassengers;
    private BigDecimal price;
    private Integer rating;

    @JsonSerialize(using = CustomOffsetDateTimeSerializer.class)
    @JsonDeserialize(using = CustomOffsetDateTimeDeserializer.class)
    private OffsetDateTime pickupTime;

    @JsonSerialize(using = CustomInstantSerializer.class)
    @JsonDeserialize(using = CustomInstantDeserializer.class)
    private Instant createdOn;

    @JsonSerialize(using = CustomInstantSerializer.class)
    @JsonDeserialize(using = CustomInstantDeserializer.class)
    private Instant lastModifiedOn;

    @OneToMany(targetEntity=TripWaypoint.class, mappedBy = "booking", fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TripWaypoint> tripWayPoints;

}