/** Project: Lab 4
 * Purpose Details: System integration, RabbitMQ
 * Course: IST 242
 * Author: Joseph Oakes
 * Date Developed: 12/11/24
 * Last Date Changed: 12/11/24
 * Rev: 12/11/24

 */
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {
    private final static String QUEUE_NAME = "pizza_queue";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // Declare the queue
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            // Create a Pizza object
            Pizza pizza = new Pizza("Margherita", "Medium", 8.99, "Cheese");

            // Serialize the Pizza object manually as a JSON-like string
            String message = String.format(
                    "{\"name\":\"%s\",\"size\":\"%s\",\"price\":%.2f,\"topping\":\"%s\"}",
                    pizza.getName(), pizza.getSize(), pizza.getPrice(), pizza.getTopping()
            );

            // Publish the message
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}

