package cn.jinronga.rabbitmq01.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RabbitMqConfiguration
 * @Author 郭金荣
 * @Date 2021/5/7 19:55
 * @Description RabbitMqConfiguration
 * @Version 1.0
 */
@Configuration
public class RabbitMqFanOutConfiguration {
    //1.声明注册fanout模式的交换机
    @Bean
    public FanoutExchange fanoutExchange() {
        //FanoutExchange fanout模式的交换机
        return new FanoutExchange("fanout_order_exchange", true, false);
    }

    //2.创建队列
    @Bean
    public Queue smsQueue() {
        return new Queue("sms.fanout.queue", true);
    }

    @Bean
    public Queue jinRongQueue() {
        return new Queue("jinRong.fanout.queue", true);
    }

    @Bean
    public Queue emailQueue() {
        return new Queue("email.fanout.queue", true);
    }

    //3.完成队列与交换机绑定关系
    @Bean
    public Binding smsBinding() {
        return BindingBuilder.bind(smsQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding jinRongBinding() {
        return BindingBuilder.bind(jinRongQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding emailBinding() {
        return BindingBuilder.bind(smsQueue()).to(fanoutExchange());
    }
}
