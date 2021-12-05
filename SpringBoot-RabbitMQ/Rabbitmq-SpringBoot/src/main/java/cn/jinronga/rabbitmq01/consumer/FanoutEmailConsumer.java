package cn.jinronga.rabbitmq01.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName FanoutEmailConsumer
 * @Author 郭金荣
 * @Date 2021/5/7 21:05
 * @Description FanoutEmailConsumer
 * @Version 1.0
 */
@Component
@RabbitListener(queues = {"email.fanout.queue"})
public class FanoutEmailConsumer {
    @RabbitHandler
    public void reviceMessage(String message) {
        System.out.println("sms接收到了的订单信息是：" + message);
    }
}
