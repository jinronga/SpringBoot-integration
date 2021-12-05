package cn.jinronga.routing;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//简单模式
public class Consumer {


    public static void main(String[] args) {


        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("118.31.21.193");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setVirtualHost("/");
        Channel channel = null;
        Connection connection = null;
        try {
            connection = factory.newConnection("消费者");
            channel = connection.createChannel();
            channel.queueDeclare("queue1", false, false, false, null);

            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            };
            channel.basicConsume("queue1", true, deliverCallback, consumerTag -> {
            });


        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && connection.isOpen()) {
                try {
                    connection.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (channel != null && channel.isOpen()) {
                try {
                    channel.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
