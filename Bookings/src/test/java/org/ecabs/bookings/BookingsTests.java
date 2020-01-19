package org.ecabs.bookings;

import org.ecabs.bookings.domain.Booking;
import org.ecabs.bookings.domain.TripWaypoint;
import org.ecabs.bookings.infrastructure.messagebroker.BookingProducerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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
}
