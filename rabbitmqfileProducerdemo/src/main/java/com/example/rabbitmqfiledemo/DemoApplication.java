package com.example.rabbitmqfiledemo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.rabbitmqfiledemo.pojo.SimpleMessage;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	
	@Autowired
	RabbitTemplate rabbitTemplate;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SimpleMessage simpleMessage = new SimpleMessage();
		simpleMessage.setName("Simple message 1");
		simpleMessage.setDescription("simple description 1");
		
		rabbitTemplate.convertAndSend("testFileExchange", "testFileRouting", simpleMessage);
		
		simpleMessage.setName("Simple message 2");
		simpleMessage.setDescription("simple description 2");
		rabbitTemplate.convertAndSend("testFileExchange", "testFileRouting", simpleMessage);
		
	}

}
