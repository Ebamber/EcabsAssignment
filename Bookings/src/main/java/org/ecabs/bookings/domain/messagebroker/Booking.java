package org.ecabs.bookings.domain.messagebroker;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.key.OffsetDateTimeKeyDeserializer;
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
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss.SSSSSSx")
    @JsonProperty("pickupTime")
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
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss.SSSSSSx")
    @JsonProperty("createdOn")
    private Instant createdOn;

    @Column(name="lastModifiedOn")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss.SSSSSSx")
    @JsonProperty("lastModifiedOn")
    private Instant lastModifiedOn;

    @JsonManagedReference
    @OneToMany(mappedBy = "booking")
    @JsonProperty("tripWayPoints")
    private List<TripWaypoint> tripWayPoints;

}