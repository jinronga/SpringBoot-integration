package cn.jinronga.simple;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerSimple {
    private final static String QUEUE_NAME = "hello";
    private final static String RABBITMQ_HOST = "118.31.21.193";
    private final static String USER_NAME = "admin";
    private final static String PASS_WORD = "admin";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RABBITMQ_HOST);
        factory.setUsername(USER_NAME);
        factory.setPassword(PASS_WORD);
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            System.out.println("等待接受消息。。。");
            //推送的消息如何进行消费的接口回调
           DeliverCallback deliverCallback =(consumerTag, delivery)->{
               String message = new String(delivery.getBody());
               System.out.println(message);
           };
           //取消消费的一个回调接口，如在消费的时候队列被删除了
            CancelCallback cancelCallback=(consumerTag)->{
                System.out.println("消息被中断。。。");
            };
            /**
             * 消费者消费消息
             * 1.消费哪个队列
             * 2.消费成功之后是否要自动应答 true 代表自动应答 false 手动应答
             * 3.消费者未成功消费的回调
             */
            channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);

        }
    }
}
