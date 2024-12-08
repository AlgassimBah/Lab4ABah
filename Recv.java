import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Recv {
    private final static String QUEUE_NAME = "pizza_queue";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        // Handle received messages
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");

            // Deserialize the JSON-like string back to a Pizza object
            String[] parts = message.replace("{", "").replace("}", "").replace("\"", "").split(",");
            String name = parts[0].split(":")[1];
            String size = parts[1].split(":")[1];
            double price = Double.parseDouble(parts[2].split(":")[1]);
            String topping = parts[3].split(":")[1];

            Pizza pizza = new Pizza(name, size, price, topping);
            System.out.println("Deserialized Pizza Object: " + pizza);
        };

        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
