package com.careerdeves.weatherapi.controllers;

import com.careerdeves.weatherapi.models.CurrentWeather;
import com.careerdeves.weatherapi.models.CurrentWeather;
import com.careerdeves.weatherapi.models.CurrentWeatherReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/current")
public class CurrentWeatherController {
    @Autowired
    private Environment environment;
// create a base url  final, so it can't be changed  note the format
    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    //@GetMapping("/city/{cityName}")
    @GetMapping("")
    public ResponseEntity<?> getCurrentWeatherByCity (RestTemplate restTemplate, @PathVariable String cityName) {

        try {
            //String city = "providence";
            String units = "imperial";

            String apiKey = environment.getProperty("OW_API_KEY");
            String queryString = "?q=" + cityName + "&appid=" + apiKey + "&units=" + units; //imperial";
            String openWeatherUrl = BASE_URL + queryString;
            //System.out.println(add url here to test);
            CurrentWeather owRes = restTemplate.getForObject(openWeatherUrl, CurrentWeather.class);

            assert owRes != null;
            //System.out.println("City: " + openWeatherResponse.getName());   ***** Commented Out for new weather report *****
            //System.out.println("Temp: " + openWeatherResponse.getMain().getTemp() + "F");
            //System.out.println("Desc: " + openWeatherResponse.getWeather()[0].getDescription());
            CurrentWeatherReport report;
            report = new CurrentWeatherReport(
                    owRes.getName(),
                    owRes.getCoord(),
                    owRes.getMain(),
                    owRes.getWeather()[0],
                    units
            );

            System.out.println(owRes);  // return ResponseEntity.ok(openWeatherResponse);   ***** Commented Out for new weather report *****
            System.out.println(report);

            return ResponseEntity.ok(report);

        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(404).body("City Not Found!  You entered: " + cityName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    //http:/localhost:8080/api/current?city = boston&units=imperial
    //http:/localhost:8080/api/current/city?name=boston$units=imperial

    @GetMapping("")
    public ResponseEntity<?> getCurrentWeatherByCityRequestPrams (
            RestTemplate restTemplate,
            @RequestParam String //19 minutes into 4name
            //@RequestParam String units
    ){
        try {
            String city = "name";
            String units = "imperial";

            String apiKey = environment.getProperty("OW_API_KEY");
            String queryString = "?q=" + city + "&appid=" + apiKey + "&units=" + units; //imperial";
            String openWeatherUrl = BASE_URL + queryString;

            return ResponseEntity.ok().body("Test Request Param");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }
}


