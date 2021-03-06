package com.careerdeves.weatherapi.controllers;

import com.careerdeves.weatherapi.models.CurrentWeather;
import com.careerdeves.weatherapi.models.CurrentWeatherReport;
import com.careerdeves.weatherapi.validation.WeatherValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;

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
            //String city = "providence";
            String units = "imperial";

            String apiKey = environment.getProperty("OW_API_KEY");
            String queryString = "?q=" + cityName + "&appid=" + apiKey + "&units=" + units; //imperial";
            String openWeatherUrl = BASE_URL + queryString;
            //System.out.println(add url here to test);
            CurrentWeather owRes = restTemplate.getForObject(openWeatherUrl, CurrentWeather.class);

            assert owRes != null;


            return ResponseEntity.ok(owRes.createReport(units));

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

    @GetMapping("/city")
    public ResponseEntity<?> getCurrentWeatherByCityRequestPrams (
            RestTemplate restTemplate,
            @RequestParam (value = "name") String cityName,
            @RequestParam(defaultValue = "imperial") String units
    ){
        try {
            //ArrayList<String> validationErrors = new ArrayList<>();
            HashMap<String,String> validationErrors = WeatherValidation.validateQuery(cityName,units);
           //if (cityName.trim().equals("")) {
            if (validationErrors.size() !=0) {
                return ResponseEntity.badRequest().body(validationErrors);
            }
//                validationErrors.add("City name required");
//            }else if (
//                    !cityName.replaceAll("[^a-zA-Z -]", "").equals(cityName)
//            ){
//
//                validationErrors.add("Invalid City Name");
//            }
//            if (!units.equals("metric") && !units.equals("imperial")){
//                validationErrors.add("Units must be metric OR imperial");
//            }
//
//            if(validationErrors.size() != 0 ){
//                return ResponseEntity.badRequest().body(validationErrors);  //69 min
//            }
//
//            // The two lines below now use request prams above.
//            //String city = "name";
//            //String units = "imperial";
//
//            System.out.println("Name:" + cityName+ " - units:" + units);  //Test cityName

            String apiKey = environment.getProperty("OW_API_KEY");
            String queryString = "?q=" + cityName + "&appid=" + apiKey + "&units=" + units; //imperial";
            String openWeatherUrl = BASE_URL + queryString;

             CurrentWeather owRes = restTemplate.getForObject(openWeatherUrl, CurrentWeather.class);
 // because I added it to the controller>>>
            // return ResponseEntity.ok().body(owRes);
            assert owRes != null;
            return ResponseEntity.ok(owRes.createReport(units));
        } catch (HttpClientErrorException.NotFound e) { //cityName check
            return ResponseEntity.status(404).body("City Not Found!  You entered: " + cityName);

        }catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }
}

//@ ln 52 owRes..
//System.out.println("City: " + openWeatherResponse.getName());   ***** Commented Out for new weather report *****
//System.out.println("Temp: " + openWeatherResponse.getMain().getTemp() + "F");
//System.out.println("Desc: " + openWeatherResponse.getWeather()[0].getDescription());
//because we added this to the controller >>>
// >>> CurrentWeatherReport report;
//            report = new CurrentWeatherReport(
//                    owRes.getName(),
//                    owRes.getCoord(),
//                    owRes.getMain(),
//                    owRes.getWeather()[0],
//                    units
//            );
//
//            System.out.println(owRes);  // return ResponseEntity.ok(openWeatherResponse);   ***** Commented Out for new weather report *****
//            System.out.println(report);
