import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Weather {
    private final String accessKey;
    private final String url;

    public Weather(String accessKey) {
        this.accessKey = accessKey;
        this.url = "https://api.weather.yandex.ru/v2/forecast?lat=55.75&lon=37.62";
    }

    public void fetchWeatherData() {
        try {
            URL url = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("X-Yandex-Weather-Key", this.accessKey);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();

            String jsonResponse = response.toString();
            System.out.println(jsonResponse);

            
            int sum = 0;
            int count = 0;

            
            String[] parts = jsonResponse.split("\"temp\":");
            for (int i = 1; i < parts.length; i++) {
                int endIndex = parts[i].indexOf(",");
                if (endIndex != -1) {
                    sum += Integer.parseInt(parts[i].substring(0, endIndex));
                    count++;
                }
            }

            if (count > 0) {
                double average = (double) sum / count;
                System.out.println("Average Temperature: " + average);
            } else {
                System.out.println("No temperature data found");
            }

        } catch (Exception e) {
            System.err.println("There has been a problem with your fetch operation: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Weather weatherApp = new Weather("3c83def6-df04-41a2-910f-da23d219f69f");
        weatherApp.fetchWeatherData();
    }
}
