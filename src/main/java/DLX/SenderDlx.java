package DLX;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Vector;

public class SenderDlx {

    private static String NAME_EXCHAGE = "mainExchange";

    public static void main(String[] args0) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("172.20.0.2");
        factory.setUsername("admin");
        factory.setPassword("passw123");
        factory.setPort(5672);

        try( Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();
            AMQP.Confirm.SelectOk selectOk = channel.confirmSelect();

            channel.exchangeDeclare(NAME_EXCHAGE,"topic");

            //criar a mensagem
            String message = "Hello! This is a test!";
            String routingKey = "bkConsumer";
            //enviar a mensagem
            channel.basicPublish(NAME_EXCHAGE, routingKey, null, message.getBytes());

            System.out.print("[x] Done'");
        }
    }
}