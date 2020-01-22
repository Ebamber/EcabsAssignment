package org.ecabs.bookings.infrastructure.messagebroker;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Value("${ecabs.message.audit}")
    private String auditQueue;

    @Value("${ecabs.message.booking.edit}")
    private String editQueue;

    @Value("${ecabs.message.booking.delete}")
    private String deleteQueue;

    @Value("${ecabs.message.booking.add}")
    private String addQueue;

    @Value("${ecabs.message.exchange}")
    String messageExchange;

    @Value("${ecabs.message.booking.exchange}")
    String bookingExchange;

    @Bean
    public Queue addQueue() {
        return new Queue(addQueue, true);
    }

    @Bean
    public Queue deleteQueue() {
        return new Queue(deleteQueue, true);
    }

    @Bean
    public Queue editQueue() {
        return new Queue(editQueue, true);
    }

    @Bean
    public Queue auditQueue() {
        return new Queue(auditQueue, true);
    }

    @Bean
    public Binding addBinding(Queue addQueue, DirectExchange bookingExchange){
         return BindingBuilder.bind(addQueue).to(bookingExchange).with("ADD");
    }

    @Bean
    public Binding editBinding(Queue editQueue, DirectExchange bookingExchange){
        return BindingBuilder.bind(editQueue).to(bookingExchange).with("EDIT");
    }

    @Bean
    public Binding deleteBinding(Queue deleteQueue, DirectExchange bookingExchange){
        return BindingBuilder.bind(deleteQueue).to(bookingExchange).with("DELETE");
    }

    @Bean
    public Binding auditBinding(Queue auditQueue, FanoutExchange messageExchange){
        return BindingBuilder.bind(auditQueue).to(messageExchange);
    }

    @Bean
    public Binding exchangeBinding(DirectExchange bookingExchange, FanoutExchange messageExchange){
        return BindingBuilder.bind(bookingExchange).to(messageExchange);
    }

    @Bean
    public FanoutExchange messageExchange() {
        return new FanoutExchange(messageExchange);
    }

    @Bean
    public DirectExchange bookingExchange() {
        return new DirectExchange(bookingExchange);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
