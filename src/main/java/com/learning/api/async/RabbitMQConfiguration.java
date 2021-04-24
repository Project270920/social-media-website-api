package com.learning.api.async;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;


@Configuration
@Setter
@Getter
public class RabbitMQConfiguration
{
	public final static String QUEUE = "queue2";
	public final static String EXCHANGE = "topic-exchange";
	public final static String ROUTING_KEY = "k1";

	@Bean
	public Queue queue() {
		return new Queue(QUEUE, true, false, false);
	}

	@Bean
	public DirectExchange exchangeDirect() {
		return new DirectExchange(EXCHANGE);
	}
	
	@Bean
	public FanoutExchange exchangeFanout() {
		return new FanoutExchange(EXCHANGE);
	}
	
	@Bean
	public HeadersExchange exchangeHeaders() {
		return new HeadersExchange(EXCHANGE);
	}
	
	@Bean
	public TopicExchange exchangeTopic() {
		return new TopicExchange(EXCHANGE);
	}

	@Bean
	public Binding bindingDirectExhange(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}
	
	@Bean
	public Binding bindingTopicExhange(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}
/*	
	@Bean
	public Binding bindingFanoutExhange(Queue queue, FanoutExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingkey);
	}
	
	@Bean
	public Binding bindingHeaderExhange(Queue queue, HeadersExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingkey);
	}
*/

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	
	@Bean
	public AmqpTemplate template(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}
}
