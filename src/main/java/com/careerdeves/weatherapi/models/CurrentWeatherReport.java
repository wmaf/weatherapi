package com.careerdeves.weatherapi.models;

public class CurrentWeatherReport {
    private String name;
    private float lon;
    private float lat;
    private float temp;
    private float feelsLike;
    private float tempMin;
    private float tempMax
    private float pressure;
    private float humidity;
    private String main;
    private String description;

    public CurrentWeatherReport(
            String name,
            CurrentWeather.Coords coords,
            CurrentWeather.Main main,
            CurrentWeather.Weather weather
    ) {
        this.name = name;
        lon = coords.getLon();
        lon = coords.getLat();
        temp = main.getTemp();
        feelsLike = main.getFeels_like();
        tempMin = main.getTemp_min();
        tempMax = main.getTemp_max();
        pressure = main.getPressure();
        humidity = main.getHumidity();
        this.main = weather.getMain();
        description = weather.getDescription();

this.
    }

    public String getName() {
        return name;
    }

    public float getLon() {
        return lon;
    }

    public float getLat() {
        return lat;
    }

    public float getTemp() {
        return temp;
    }

    public float getFeelsLike() {
        return feelsLike;
    }

    public float getTempMin() {
        return tempMin;
    }

    public float getTempMax() {
        return tempMax;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }
}
