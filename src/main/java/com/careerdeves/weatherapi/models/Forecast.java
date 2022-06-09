package com.careerdeves.weatherapi.models;

import com.careerdeves.weatherapi.models.CurrentWeather.*;


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

    public static class ForecastWeather extends CurrentWeather{


    }

    public City getCity() {
        return city;
    }

    public ForecastWeather[] getList() {
        return list;
    }
}
