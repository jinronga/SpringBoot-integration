package cn.jinronga.rabbitmq01.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName FanoutDuanxinConsumer
 * @Author 郭金荣
 * @Date 2021/5/7 21:02
 * @Description FanoutDuanxinConsumer
 * @Version 1.0
 */
@Component
@RabbitListener(queues = {"jinRong.fanout.queue"})
public class FanoutJinRongConsumer {
    @RabbitHandler
    public void reviceMessage(String message) {
        System.out.println("duanxin接收到了的订单信息是：" + message);
    }
}
