package com.careerdeves.weatherapi.models;

import com.careerdeves.weatherapi.models.Forecast.ForecastWeather;

public class ForcastReport {

    private final String cityName;
    private final String country;
    private final int population;
    private ForecastReportEntry[] reports;

    public ForcastReport(Forecast forecast) {
        cityName = forecast.getCity().getName();
        country = forecast.getCity().getCountry();
        population = forecast.getCity().getPopulation();

        reports = new   ForecastReportEntry[forecast.getList().length];

        for (int i = 0; i < forecast.getList().length; i++) {
            reports[i] = new ForecastReportEntry(forecast.getList()[i]);
           // reports.add(new ForecastReportEntry(forecast.getList()[i]));
        }
    }

    public static class ForecastReportEntry{
        private final String dateTime;
        private final String description;
        private final String temp;
        private final String percentageOfPrecipitation;

    public ForecastReportEntry(Forecast.ForecastWeather wd) {
        description = wd.getWeather()[0].getMain() + " : " + wd.getWeather()[0].getDescription();
        dateTime = wd.getDt_txt();
        temp = wd.getMain().getTemp() + "°F";  // needs to be refactored to reflect proper data type
        percentageOfPrecipitation = (wd.getPop() * 100) + "%";
    }

        public String getDescription() {
            return description;
        }

        public String getDateTime() {
            return dateTime;
        }

        public String getTemp() {
            return temp;
        }

        public String getPercentageOfPrecipitation() {
            return percentageOfPrecipitation;
        }
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountry() {
        return country;
    }

    public int getPopulation() {
        return population;
    }

    public ForecastReportEntry[] getReports() {
        return reports;
    }

//    public ForecastReportEntry[] getReports() {
//        return reports;
//    }
}
