package cn.jinronga.rabbitmq01.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName FanoutSmsConsumer
 * @Author 郭金荣
 * @Date 2021/5/7 21:00
 * @Description FanoutSmsConsumer
 * @Version 1.0
 */
@Component
@RabbitListener(queues = {"sms.fanout.queue"})
public class FanoutSmsConsumer {
    @RabbitHandler
    public void reviceMessage(String message) {
        System.out.println("sms接收到了的订单信息是：" + message);
    }
}
