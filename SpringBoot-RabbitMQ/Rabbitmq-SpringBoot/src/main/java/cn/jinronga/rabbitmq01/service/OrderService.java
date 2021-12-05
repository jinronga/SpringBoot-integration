package cn.jinronga.rabbitmq01.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

/**
 * @ClassName OrderService
 * @Author 郭金荣
 * @Date 2021/5/7 19:34
 * @Description OrderService
 * @Version 1.0
 */
@Service
public class OrderService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void fanoutOrder(String userid, String productid, int num) {
        //1.根据商品id查询库存是否足够
        System.out.println("用户id：" + userid + "产品id:" + productid + "购买数量：" + num);
        //2.保存订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生产成功！" + orderId);
        //3.通过MQ来完成消息的分发
        String exchangeName = "fanout_order_exchange";
        String routingKey = "";
        rabbitTemplate.convertAndSend(exchangeName, routingKey, orderId);
    }

    public void directOrder(String userid, String productid, int num) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();

        //1.根据商品id查询库存是否足够
        System.out.println("用户id：" + userid + "产品id:" + productid + "购买数量：" + num);
        //2.保存订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生产成功！" + orderId);
        //3.通过MQ来完成消息的分发
        String exchangeName = "direct_order_exchange";
        rabbitTemplate.convertAndSend(exchangeName, "sms", orderId);
        rabbitTemplate.convertAndSend(exchangeName, "jinronga", orderId);
        rabbitTemplate.convertAndSend(exchangeName, "email", orderId);
    }
}
