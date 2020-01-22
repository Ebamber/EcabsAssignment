package org.ecabs.bookings;

import org.ecabs.bookings.domain.messagebroker.Booking;
import org.ecabs.bookings.domain.messagebroker.TripWaypoint;
import org.ecabs.bookings.infrastructure.messagebroker.BookingProducerService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableRabbit
@TestPropertySource(locations = "classpath:application-test.yml")
public class BookingsTests {
//nothing in here is a conventional unit test, I was just using them to debug my own code and gather information for postman
	private Booking mockBooking;

	@Autowired
	private BookingProducerService producer;

	@Before
	public void init(){
		List<TripWaypoint> waypoints = new ArrayList<>();

		mockBooking = new Booking(UUID.randomUUID(),
				"foo",
				"21227244",
				OffsetDateTime.now(),
				true,
				Integer.valueOf(0),
				Integer.valueOf(0),
				BigDecimal.TEN,
				Integer.valueOf(0),
				Instant.now(),
				Instant.now(),
				waypoints);
		waypoints.add(
				new TripWaypoint(
						UUID.randomUUID(),
						mockBooking,
						true,
						"Rabat",
						1.0,
						1.0,
						Instant.now()));
		mockBooking.setTripWayPoints(waypoints);
	}

	@Test
	public void shouldProduceAddBooking(){
		producer.sendAdd(mockBooking);
	}

	@Test
	public void shouldProduceDeleteBooking(){
		producer.sendDelete(mockBooking);
	}

	@Test
	public void shouldProduceEditBooking(){
		producer.sendEdit(mockBooking);
	}

	@Test
	public void shouldAuditBooking(){
		producer.sendAudit(mockBooking);
	}

	@Test
	public void shouldPrintJson() throws JSONException {
		System.out.println(getJSON());
	}

	//just using this to create a json body for my postman tests - I know it's not really conventional
	private String getJSON() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("bookingId", mockBooking.getBookingId());
		jsonObject.put("passengerName", mockBooking.getPassengerName());
		jsonObject.put("passengerContactNumber", mockBooking.getPassengerContactNumber());
		jsonObject.put("pickupTime", mockBooking.getPickupTime());
		jsonObject.put("asap", mockBooking.getAsap());
		jsonObject.put("waitingTime", mockBooking.getWaitingTime());
		jsonObject.put("noOfPassengers", mockBooking.getNoOfPassengers());
		jsonObject.put("price", mockBooking.getPrice());
		jsonObject.put("rating", mockBooking.getRating());
		jsonObject.put("createdOn", mockBooking.getCreatedOn());
		jsonObject.put("lastModifiedOn", mockBooking.getLastModifiedOn());
		JSONObject tripWaypointsJson = new JSONObject();
		tripWaypointsJson.put("waypointId", mockBooking.getTripWayPoints().get(0).getWaypointId());
		tripWaypointsJson.put("bookingId", mockBooking.getTripWayPoints().get(0).getBooking().getBookingId());
		tripWaypointsJson.put("lastStop", mockBooking.getTripWayPoints().get(0).getLastStop());
		tripWaypointsJson.put("locality", mockBooking.getTripWayPoints().get(0).getLocality());
		tripWaypointsJson.put("lat", mockBooking.getTripWayPoints().get(0).getLat());
		tripWaypointsJson.put("lng", mockBooking.getTripWayPoints().get(0).getLng());
		tripWaypointsJson.put("tripWayPointTimestamp", mockBooking.getTripWayPoints().get(0).getTripWayPointTimestamp());
		jsonObject.put("tripWayPoints", tripWaypointsJson);
		return jsonObject.toString();
	}
}
