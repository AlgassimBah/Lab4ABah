/** Project: Lab 4
 * Purpose Details: System integration, RabbitMQ
 * Course: IST 242
 * Author: Joseph Oakes
 * Date Developed: 12/11/24
 * Last Date Changed: 12/11/24
 * Rev: 12/11/24

 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebServiceCaller {
    public static void main(String[] args) {
        try {
            // Specify the URL of the web service
            String url = "http://localhost:8000/hello";

            // Create a URL object
            URL obj = new URL(url);

            // Open a connection to the URL
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Set the request method
            con.setRequestMethod("GET");

            // Set request headers if needed
            // con.setRequestProperty("Content-Type", "application/json");

            // Get the response code
            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            // Read the response from the web service
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Print the response
            System.out.println("Response: " + response.toString());

            // Parse the JSON response as needed

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}