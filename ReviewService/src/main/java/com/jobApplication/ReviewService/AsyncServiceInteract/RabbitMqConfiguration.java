package com.jobApplication.ReviewService.AsyncServiceInteract;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration
{
    protected final String reviewQueue = "reviewRating.Queue";

    protected final String reviewExchange = "reviewRating.Exchange";

    protected final String revireRouteKey = "reviewRating.RouteKey";

    @Bean
    public Queue queue()
    {
        return QueueBuilder.durable(reviewQueue).build();
    }

    @Bean
    public Exchange topicExchange()
    {
        return ExchangeBuilder.topicExchange(reviewExchange).durable(true).build();
    }

    @Bean
    public Binding binding()
    {
        return BindingBuilder.bind(queue()).to(topicExchange()).with(revireRouteKey).noargs();
    }

    @Bean
    public MessageConverter messageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        rabbitTemplate.setExchange(reviewExchange);
        return rabbitTemplate;

    }

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory)
    {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }
}
