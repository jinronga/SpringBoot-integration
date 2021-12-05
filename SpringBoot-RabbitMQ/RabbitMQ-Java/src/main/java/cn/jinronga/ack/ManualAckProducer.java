package cn.jinronga.ack;

import cn.jinronga.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class ManualAckProducer {

    private static final String QUEUE_NAME = "ack_queue";

    public static void main(String[] args) {
        try (Channel channel = RabbitMqUtils.getChannel();) {
            //让消息持久化
            boolean durable=true;
            channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
            // 设置为不公平分发
            channel.basicQos(5);

            //从控制台当中接受信息
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String message = scanner.next();
                channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
                System.out.println("生产者生产消息:" + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
