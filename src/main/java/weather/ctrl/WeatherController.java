package weather.ctrl;


import tk.plogitech.darksky.api.jackson.DarkSkyJacksonClient;
import tk.plogitech.darksky.forecast.APIKey;
import tk.plogitech.darksky.forecast.ForecastRequest;
import tk.plogitech.darksky.forecast.ForecastRequestBuilder;
import tk.plogitech.darksky.forecast.GeoCoordinates;
import tk.plogitech.darksky.forecast.model.*;

import java.util.List;

public class WeatherController {
    
    public static String apiKey = "ab5c55091bfde0864c41b337f1c66af5";
    private DarkSkyJacksonClient client = new DarkSkyJacksonClient();

    public void process(GeoCoordinates location) throws MyException {
        System.out.println("process "+location); //$NON-NLS-1$
		//Forecast data = getData();

        Forecast forecast = try_catch_test(client,apiRequest(apiKey,location));
        double highTemp = getHighestTemp(location);
        double averageTemp = getAverageTemp(location);
		
		//TODO implement Error handling 
		
		//TODO implement methods for
		// highest temperature 
		// average temperature 
		// count the daily values
		
		// implement a Comparator for the Windspeed
		
	}
    public Forecast try_catch_test(DarkSkyJacksonClient client, ForecastRequest forecastRequest) throws MyException {
        Forecast forecast;
        try {
            forecast = client.forecast(forecastRequest);

        }
        catch (Exception e) {
            System.out.println("try_catch_test error");
            System.out.println("trying again....:");
            throw new MyException("API connection error");

        }
        return forecast;
    }
    public ForecastRequest apiRequest(String apiKey, GeoCoordinates location) {

        return new ForecastRequestBuilder()
                .key(new APIKey("ab5c55091bfde0864c41b337f1c66af5"))
                .location(location)
                .build();
    }
    public Double getHighestTemp(GeoCoordinates location) throws MyException {

        DarkSkyJacksonClient client = new DarkSkyJacksonClient();
        Forecast forecast = try_catch_test(client,apiRequest(apiKey,location));
        List<DailyDataPoint> dailyDataPoints = forecast.getDaily().getData();

        return dailyDataPoints.stream().mapToDouble(DailyDataPoint::getTemperatureHigh).max().orElseThrow();
    }

    public Double getAverageTemp(GeoCoordinates location) throws MyException {

        DarkSkyJacksonClient client = new DarkSkyJacksonClient();
        Forecast forecast = try_catch_test(client,apiRequest(apiKey,location));
        List<DailyDataPoint> dailyDataPoints = forecast.getDaily().getData();

        return dailyDataPoints.stream().mapToDouble(DailyDataPoint::getTemperatureHigh).average().orElseThrow();
    }

    public GeoCoordinates getLocation(Longitude x, Latitude y) {
        return new GeoCoordinates(x,y);
    }



    public static void main(String[] args) {
        WeatherController wc = new WeatherController();

        Longitude x = new Longitude(40.0);
        Latitude y = new Latitude(18.0);

        GeoCoordinates location = wc.getLocation(x,y);
       // System.out.println(wc.getHighestTemp(location));
      //  System.out.println(wc.getAverageTemp(location));
    }
}
