package DLX;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class ReceiverDlx {
    private static final String CONSUMER_QUEUE = "queueConsumer";

    public static void main(String[] args0) throws Exception {
        //primeiro criar a conexão
        //setar as informações para cria-la
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("172.20.0.2");
        factory.setUsername("admin");
        factory.setPassword("passw123");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        System.out.println(channel);

        DeliverCallback  deliverCallback = (ConsumeTag, delivery) ->{
            String message = new String(delivery.getBody(),"UTF-8");
            System.out.println("[*] Received message: '" + message + "'");
        };

        channel.basicConsume(CONSUMER_QUEUE, true, deliverCallback, ConsumerTag->{});
    }
}
