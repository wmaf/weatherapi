package com.careerdeves.weatherapi.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CurrentWeatherReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private float lon;
    private float lat;
    private float temp;
    private String units;
    private float feelsLike;
    private float tempMin;
    private float tempMax;
    private float pressure;
    private float humidity;
    private String main;
    private String description;

public CurrentWeatherReport() {

}


    public CurrentWeatherReport(
            String name,
            CurrentWeather.Coords coords,
            CurrentWeather.Main main,
            CurrentWeather.Weather weather,
            String units) {


        this.name = name;
        this.units = units;
        lon = coords.getLon();
        lat = coords.getLat();
        temp = main.getTemp();
        feelsLike = main.getFeels_like();
        tempMin = main.getTemp_min();
        tempMax = main.getTemp_max();
        pressure = main.getPressure();
        humidity = main.getHumidity();
        this.main = weather.getMain();
        description = weather.getDescription();


    }

    public CurrentWeatherReport(String name, CurrentWeather.Coords coord, CurrentWeather.Main main, CurrentWeather.Weather weather) {
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

    public String getUnits() {
        return units;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"").append(name).append('"');
        sb.append(", \"units\":\"").append(units).append('"');
        sb.append(", \"lon\":").append(lon);
        sb.append(", \"lat\":").append(lat);
        sb.append(", \"temp\":").append(temp).append("°").append(units.equals("imperial") ? "F":"C").append("\"");
        sb.append(", \"feelsLike\":").append(feelsLike).append("°").append(units.equals("imperial") ? "F":"C").append("\"");
        sb.append(", \"tempMin\":").append(tempMin).append("°").append(units.equals("imperial") ? "F":"C").append("\"");
        sb.append(", \"tempMax\":").append(tempMax).append("°").append(units.equals("imperial") ? "F":"C").append("\"");
        sb.append(", \"pressure\":").append(pressure);
        sb.append(", \"humidity\":").append(humidity);
        sb.append(", \"main\":\"").append(main).append('"');
        sb.append(", \"description\":\"").append(description).append('"');
        sb.append('}');
        return sb.toString();
    }
}
