package com.careerdeves.weatherapi.models;

public class CurrentWaether {




    private String name;

    private int timezone;

    private int vivibility;

    private Coords coord;

    private Mainlist main;


    public  String getName() {
    return name;
    }

    public int getTimezone() {
        return timezone;
    }

    public int getVivibility() {
        return vivibility;
    }

    public Coords getCoord() {
        return coord;
    }

    private static class Coords {
        private float lon;
                private float lat;

        public float getLon() {
            return lon;
        }

        public float getLat() {
            return lat;
        }
   }
}
