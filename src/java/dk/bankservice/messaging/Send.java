/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.bankservice.messaging;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import java.io.IOException;

/**
 *
 * @author marekrigan
 */
public class Send 
{
    
    public static void sendMessage(String message, BasicProperties props) throws IOException 
    {
        String taskQueueName = props.getReplyTo();
        
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("datdb.cphbusiness.dk");
	factory.setUsername("student");
	factory.setPassword("cph");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        
        channel.queueDeclare(taskQueueName, true, false, false, null);
        
        channel.basicPublish( "", taskQueueName, 
                props,
                message.getBytes());
        
        channel.close();
        connection.close();
    }
}
