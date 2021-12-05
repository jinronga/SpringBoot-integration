package cn.jinronga.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName ProducerFanout
 * @Author 郭金荣
 * @Date 2021/5/6 19:46
 * @Description ProducerFanout
 * @Version 1.0
 */
//功能：一个生产者发送的消息会被多个消费者获取。一个生产者、一个交换机、多个队列、多个消费者
public class ProducerFanout {
    public static void main(String[] args) {
        //1.创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("118.31.21.193");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("/");
        Channel channel = null;
        Connection connection = null;
        try {
            connection = connectionFactory.newConnection("生产者");
            //2.创建通道
            channel = connection.createChannel();

    /*参数1: 是否持久化，非持久化消息会存盘吗？会存盘，但是会随着重启服务器而丢失
      参数2:是否独占队列
      参数3:是否自动删除，随着最后一个消费者消息完毕消息以后是否把队列自动删除
  	  参数4:携带附属属性
    */
            //3.通过创建交换机，声明队列，绑定关系，路由key，发送消息和接受消息

            String queueName = "queue1";
            String exchangeName = "fanout-exchange";
            //6.定义路由key
            String routeKey = "";
            //7.指定交换机的类型
            String type = "fanout";

            channel.queueDeclare(queueName, true, false, false, null);
            //4.发送消息给队列queue
    /*参数1: 交换机
      参数2:队列、路由key
      参数3:消息的状态控制
  	  参数4:消息主题
    */
            //面试题：可以存在没有交换机的队列吗？不可能，虽然没有指定交换机但是一定会存在一个默认的交换机
            String message = "Hello";
            //5.准备交换机

            channel.basicPublish(exchangeName, routeKey, null, message.getBytes());
            System.out.println("消息发送成功！");


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (channel != null && channel.isOpen()) {
                try {
                    channel.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null && connection.isOpen()) {
                try {
                    connection.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

    }
}
