/**
 * 
 */
package com.example.rabbitmqfilelistenerdemo.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.rabbitmqfilelistenerdemo.messagelistener.RabbitMQMessageListener;


/**
 * @author user
 *
 */
@Configuration
public class RabbitMQConfig {

	private static final String MY_QUEUE = "testFileQueue";
	
	@Bean
	Queue myQueue() {
		return new Queue(MY_QUEUE,true);
	}
	
	@Bean
	ConnectionFactory connectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
		cachingConnectionFactory.setUsername("guest");
		cachingConnectionFactory.setPassword("guest");
		return cachingConnectionFactory;
	}
	
	
	@Bean
	MessageListenerContainer messageListenerContainer() {
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
		simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
		simpleMessageListenerContainer.setQueues(myQueue());
		simpleMessageListenerContainer.setMessageListener(new RabbitMQMessageListener());
		return simpleMessageListenerContainer;
	}
	 
	
	@Bean
	Exchange newExchange() {
		return ExchangeBuilder.topicExchange("testFileExchange")
								.durable(true).build();
		
	}
	
	@Bean
	Binding binding() {
		return BindingBuilder.bind(myQueue())
				.to(newExchange())
				.with("testFileRouting")
				.noargs();
	}
	
    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    
}
