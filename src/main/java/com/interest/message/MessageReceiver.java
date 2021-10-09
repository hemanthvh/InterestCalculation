package com.interest.message;

import javax.jms.Message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {
	
	@JmsListener(destination = "jms.message.endpoint")
	public void receiveMessage(Message msg) {
		System.out.println("Recieved Message" + msg);
	}
}
