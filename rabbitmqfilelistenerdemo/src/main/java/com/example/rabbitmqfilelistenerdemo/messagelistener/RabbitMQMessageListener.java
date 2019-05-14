package com.example.rabbitmqfilelistenerdemo.messagelistener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import com.example.rabbitmqfilelistenerdemo.pojo.SimpleMessage;
import com.google.gson.Gson;

public class RabbitMQMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		byte[] body = message.getBody();
		Gson g = new Gson();
		SimpleMessage msg = g.fromJson(new String(body) , SimpleMessage.class);
		System.out.println("message = [ "+ new String(body) + " ]");
		System.out.println("message = [ "+ msg.getName() + " ]");
		
	}
	
}
