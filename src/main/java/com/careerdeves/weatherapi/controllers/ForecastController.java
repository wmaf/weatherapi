package com.careerdeves.weatherapi.controllers;

import com.careerdeves.weatherapi.models.ForcastReport;
import com.careerdeves.weatherapi.models.Forecast;
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
@RequestMapping("/api/forecast")
public class ForecastController {

    @Autowired
    private Environment env;
    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/forecast";

    @GetMapping("/city/{cityName}")
    public ResponseEntity<?> getForecastByCity (RestTemplate restTemplate, @PathVariable String cityName) {

        try {
            String units = "imperial";
            String  apiKey = env.getProperty("OW_API_KEY");
            String queryString = "?q=" + cityName + "&units=" + units + "&appid=" + apiKey;
            String forecastURL = BASE_URL + queryString;
            //System.out.println(forecastURL);
            Forecast owRes = restTemplate.getForObject(forecastURL, Forecast.class);

            //Generate report removing null entries.

            assert owRes != null;
            ForcastReport report = new ForcastReport(owRes);


            return ResponseEntity.ok(owRes);


        }   catch (HttpClientErrorException.NotFound e) {
                return ResponseEntity.status(404).body("City Not Found!  You entered: " + cityName);
        }   catch ( Exception e) {
                System.out.println(e.getMessage());
                System.out.println(e.getClass());
                return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
