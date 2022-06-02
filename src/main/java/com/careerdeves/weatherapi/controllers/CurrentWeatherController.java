package com.careerdeves.weatherapi.controllers;


import com.careerdeves.weatherapi.models.CurrentWeather;
import com.careerdeves.weatherapi.models.CurrentWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/current")
public class CurrentWeatherController {
    @Autowired
    private Environment environment;
// create a base url  final, so it can't be changed  note the format
    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";




    @GetMapping("/city/{cityName}")
    public ResponseEntity<?> getCurrentWeatherByCity (RestTemplate restTemplate, @PathVariable String cityName) {
        try {
            String apiKey = environment.getProperty("OW_API_KEY");
            //String city = "providence";
            String querryString = "?q=" + cityName + "&appid=" + apiKey + "&units=imperial";
            String openWeatherUrl = BASE_URL + querryString;

           CurrentWeather openWeatherResponse = restTemplate.getForObject(openWeatherUrl, CurrentWeather.class);


           assert openWeatherResponse != null;
            System.out.println("City: " + openWeatherResponse.getName());
            System.out.println("Temp: " + openWeatherResponse.getMain().getTemp());
            System.out.println("Desc: " + openWeatherResponse.getWeather()[0].getDescription());



            return ResponseEntity.ok(openWeatherResponse);


        }   catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(404).body("City Not Found!  You entered: " + cityName);
        }   catch ( Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }
}
