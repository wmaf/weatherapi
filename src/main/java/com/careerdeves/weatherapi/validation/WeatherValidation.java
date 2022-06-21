package com.careerdeves.weatherapi.validation;

import java.util.HashMap;

public class WeatherValidation {

  public static HashMap<String, String> validateQuery (String city, String units) {
      HashMap<String,String> validationErrors = new HashMap<>();

      if (city.trim().equals("")) {
          validationErrors.put("city","City name required");
      }else if (
              !city.replaceAll("[^a-zA-Z -]", "").equals(city)
      ){

          validationErrors.put("city","Invalid City Name");
      }
      if (!units.equals("metric") && !units.equals("imperial")){
          validationErrors.put("units","Units must be metric or imperial");
      }

        return validationErrors;
  }


}
