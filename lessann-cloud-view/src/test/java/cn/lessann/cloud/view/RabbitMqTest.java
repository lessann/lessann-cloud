package cn.lessann.cloud.view;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/30 3:23 下午
 */
public class RabbitMqTest {

    public static void main(String[] args) throws Exception {
        customer();
    }

    public static Connection getConnection() throws Exception {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("127.0.0.1");
        //端口
        factory.setPort(5673);
        //设置账号信息，用户名、密码、vhost
        factory.setVirtualHost("/lessann");
        factory.setUsername("lessann");
        factory.setPassword("lessann");
        // 通过工程获取连接
        Connection connection = factory.newConnection();
        return connection;
    }

    private final static String QUEUE_NAME = "simple_queue";

    public static void producer() throws Exception {
        // 获取到连接
        Connection connection = getConnection();
        // 从连接中创建通道，使用通道才能完成消息相关的操作
        Channel channel = connection.createChannel();
        // 声明（创建）队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 消息内容
        String message = "Hello World!";
        // 向指定的队列中发送消息
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

        System.out.println(" [x] Sent '" + message + "'");

        //关闭通道和连接
        channel.close();
        connection.close();
    }

    public static void customer() throws Exception {
        // 获取到连接
        Connection connection = getConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            // 获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                // body 即消息体
                String msg = new String(body);
                System.out.println(" [x] received : " + msg + "!");
            }
        };
        // 监听队列，第二个参数：是否自动进行消息确认。
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
