package cn.jinronga.ack;

import cn.jinronga.utils.RabbitMqUtils;
import cn.jinronga.utils.SleepUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class AckConsumerWork01 {
    private static final String QUEUE_NAME = "ack_queue";

    public static void main(String[] args) {
        try (Channel channel = RabbitMqUtils.getChannel()){
            System.out.println("等待接受消息。。。");
            //推送的消息如何进行消费的接口回调
            DeliverCallback deliverCallback =(consumerTag, delivery)->{
                String message = new String(delivery.getBody());
//                SleepUtils.sleep(1);
                System.out.println("接收："+message);
                // 设置为不公平分发
                channel.basicQos(3);
                /**
                 * 1.消息标记tag
                 * 2.是否批量应答未应答消息
                 */
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);

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
            System.out.println("work02 消费者启动等待消费......");
            //采用手动应答
            boolean autoAck=false;
            channel.basicConsume(QUEUE_NAME,autoAck,deliverCallback,cancelCallback);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
