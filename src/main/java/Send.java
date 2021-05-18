import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class Send {
    //设置队列名
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        //工厂模式（创建工厂）
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (
                //创建连接
                Connection connection = factory.newConnection();
                //创建信道
                Channel channel = connection.createChannel()) {
            // 声明一个队列：名称、持久性的（重启仍存在此队列）、非私有的、非自动删除的
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            //声明消息内容
            String message = "Hello World!Cloud is beautiful!啊哈哈哈哈哈";
            //通过信道发布内容
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}