package com.careerdeves.weatherapi.models;

import com.careerdeves.weatherapi.models.CurrentWeather.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Forecast {
 private City city;

 private ForecastWeather[] list;

 public static class City {

     private String name;
     private Coords coord;
     private String country;
     private int population;

     public String getName() {
         return name;
     }

     public Coords getCoord() {
         return coord;
     }

     public String getCountry() {
         return country;
     }

     public int getPopulation() {
         return population;
     }
 }

    public static class ForecastWeather extends CurrentWeather {
     @JsonProperty("dt_txt")
     private String dateTime;

        private float pop;

        @JsonInclude(JsonInclude.Include.NON_NULL)

        private String name;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Coords coord;

        public String getDt_txt() {
            return dateTime;
        }

        public float getPop() {
            return pop;
        }
    }

    public City getCity() {
        return city;
    }

    public ForecastWeather[] getList() {
        return list;
    }

    public ForcastReport createReport (String units) {
        return new ForcastReport(this, units);
    }
}
