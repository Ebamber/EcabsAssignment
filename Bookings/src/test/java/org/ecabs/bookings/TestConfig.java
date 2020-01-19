package org.ecabs.bookings;

import org.ecabs.bookings.infrastructure.messagebroker.BookingProducerService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public BookingProducerService producer() {
        return new BookingProducerService();
    };
}
