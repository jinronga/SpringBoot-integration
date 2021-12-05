package cn.jinronga.work;

import cn.jinronga.utils.RabbitMqUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerWork01 {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) {
        try (Channel channel = RabbitMqUtils.getChannel()){
            System.out.println("等待接受消息。。。");
            //推送的消息如何进行消费的接口回调
            DeliverCallback deliverCallback =(consumerTag, delivery)->{
                String message = new String(delivery.getBody());
                System.out.println("接收："+message);
                System.out.println("delivery："+delivery);
                System.out.println("consumerTag："+consumerTag);

            };
            //取消消费的一个回调接口，如在消费的时候队列被删除了
            CancelCallback cancelCallback=(consumerTag)->{
                System.out.println("消费者取消消费回调逻辑。。。"+consumerTag);
            };
            /**
             * 消费者消费消息
             * 1.消费哪个队列
             * 2.消费成功之后是否要自动应答 true 代表自动应答 false 手动应答
             * 3.消费者未成功消费的回调
             */
            System.out.println("work01 消费者启动等待消费......");
            channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
