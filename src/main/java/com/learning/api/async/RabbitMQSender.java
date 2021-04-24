package com.learning.api.async;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender<T>
{	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	private String exchange;
	
	private String routingkey;	
	
	public void send(T messagae) {
		rabbitTemplate.convertAndSend(exchange, routingkey, messagae);
		System.out.println("Send msg = " + messagae);    
	}
}