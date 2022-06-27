package com.careerdeves.weatherapi.controllers;

import com.careerdeves.weatherapi.models.ForcastReport;
import com.careerdeves.weatherapi.models.Forecast;
import com.careerdeves.weatherapi.validation.WeatherValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@RestController
@RequestMapping("/api/forecast")
public class ForecastController {

    @Autowired
    private Environment env;
    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/forecast";


    @GetMapping("/city/{cityName}")
    public ResponseEntity<?> getForecastByCityPathVar (RestTemplate restTemplate,
             @RequestParam(value ="name") String cityName,
             @RequestParam(defaultValue = "imperial") String units
    ) {
    //@PathVariable String cityName
        try {
           // String units = "imperial";
            HashMap<String,String> validationErrors = WeatherValidation.validateQuery(cityName,units);

            if (validationErrors.size() !=0) {
                return ResponseEntity.badRequest().body(validationErrors);
            }

            String  apiKey = env.getProperty("OW_API_KEY");
            String queryString = "?q=" + cityName + "&units=" + units + "&appid=" + apiKey;
            String forecastURL = BASE_URL + queryString;
            //System.out.println(forecastURL);
            Forecast owRes = restTemplate.getForObject(forecastURL, Forecast.class);

            //Generate report removing null entries.
            assert owRes != null;
            return ResponseEntity.ok(owRes);


        }   catch (HttpClientErrorException.NotFound e) {
                return ResponseEntity.status(404).body("City Not Found!  You entered: " + cityName);

        }   catch ( Exception e) {
                System.out.println(e.getMessage());
                System.out.println(e.getClass());
                return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    @GetMapping("/city")
    public ResponseEntity<?> getCurrentWeatherByCityRequestPrams (
            RestTemplate restTemplate,
            @RequestParam (value = "name") String city,
            @RequestParam(defaultValue = "imperial") String units
    ){
        try {
            //String units = "imperial";
            HashMap<String,String> validationErrors = WeatherValidation.validateQuery(city,units);

            if (validationErrors.size() !=0) {
                return ResponseEntity.badRequest().body(validationErrors);
            }

            String apiKey = env.getProperty("OW_API_KEY");
            String queryString = "?q=" + city + "&units=" + units + "&appid=" + apiKey;
            String forecastURL = BASE_URL + queryString;
            //System.out.println(forecastURL);
            Forecast owRes = restTemplate.getForObject(forecastURL, Forecast.class);

            //Generate report removing null entries.
            assert owRes != null;
            return ResponseEntity.ok(owRes.createReport(units));
        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(404).body("City Not Found!  You entered: " + city);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
