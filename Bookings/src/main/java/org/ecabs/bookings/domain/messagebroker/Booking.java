package org.ecabs.bookings.domain.messagebroker;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.ecabs.bookings.infrastructure.CustomInstantDeserializer;
import org.ecabs.bookings.infrastructure.CustomInstantSerializer;
import org.ecabs.bookings.infrastructure.CustomOffsetDateTimeDeserializer;
import org.ecabs.bookings.infrastructure.CustomOffsetDateTimeSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="bookings")
public class Booking implements Serializable {

    @Id
    @Getter @Setter
    @Column(name="bookingId")
    private UUID bookingId;

    @Column(name="passengerName")
    @Getter @Setter
    private String passengerName;

    @Column(name="passengerContactNumber")
    @Getter @Setter
    private String passengerContactNumber;

    @Column(name="pickupTime")
    @Getter @Setter
    @JsonSerialize(using = CustomOffsetDateTimeSerializer.class)
    @JsonDeserialize(using = CustomOffsetDateTimeDeserializer.class)
    private OffsetDateTime pickupTime;

    @Column(name="asap")
    @Getter @Setter
    private Boolean asap;

    @Column(name="waitingTime")
    @Getter @Setter
    private Integer waitingTime;

    @Column(name="noOfPassengers")
    @Getter @Setter
    private Integer noOfPassengers;

    @Column(name="price")
    @JsonProperty("price")
    @Getter @Setter
    private BigDecimal price;

    @Column(name="rating")
    @Getter @Setter
    private Integer rating;

    @Column(name="createdOn")
    @Getter @Setter
    @JsonSerialize(using = CustomInstantSerializer.class)
    @JsonDeserialize(using = CustomInstantDeserializer.class)
    private Instant createdOn;

    @Column(name="lastModifiedOn")
    @Getter @Setter
    @JsonSerialize(using = CustomInstantSerializer.class)
    @JsonDeserialize(using = CustomInstantDeserializer.class)
    private Instant lastModifiedOn;

    @JsonManagedReference
    @Getter @Setter
    @OneToMany(mappedBy = "booking")
    private List<TripWaypoint> tripWayPoints;

}