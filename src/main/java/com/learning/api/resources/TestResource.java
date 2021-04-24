package com.learning.api.resources;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


@RestController
@RequestMapping("ampq")
public class TestResource {
	
//	@Autowired
//	private RabbitTemplate rabbitTemplate;
	
	@GetMapping("test")
	public ResponseEntity<?> test()					{
		return new ResponseEntity<String>("Test Successfull", HttpStatus.OK);
	}

	@GetMapping(value = "/sender")
	public String producer(@RequestParam("msg") String message) throws IOException, TimeoutException {
		
	//	rabbitTemplate.convertAndSend(RabbitMQConfiguration.EXCHANGE, RabbitMQConfiguration.ROUTING_KEY, message);
		config(message);
		return "Message sent to the RabbitMQ JavaInUse Successfully";
	}
	
	public void config(String msg) throws IOException, TimeoutException
	{
		ConnectionFactory factory = new ConnectionFactory();
		Connection conn = factory.newConnection();
		Channel channel = conn.createChannel();
		
		channel.basicPublish("", "test_queue", null, msg.getBytes());
	}
}