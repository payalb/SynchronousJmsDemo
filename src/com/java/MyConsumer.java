package com.java;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MyConsumer {
	public static void main(String[] args) throws JMSException {
		ConnectionFactory factory= new ActiveMQConnectionFactory("tcp://dell-PC:61616");
		Connection connection=factory.createConnection();
		Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination queue=session.createQueue("Queue1");
		connection.start();
		MessageConsumer consumer=session.createConsumer(queue);
		Message message=consumer.receive(); //Blocking call
		//This line will wait till it receives  message from queue
		if(message instanceof TextMessage) {
		TextMessage tm= (TextMessage) message;
		System.out.println(tm.getText());
		}else {
			System.out.println("Invalid format"+ message);
		}
		session.close();
		connection.close();
		
		
	}
}
