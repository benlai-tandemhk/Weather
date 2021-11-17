package com.zai.weather.dto;

import lombok.Data;

@Data
public class WeatherStackResponse {


    private Location location;
    private Current current;
    private Error error;
    private Boolean success;

    @Data
    public class Location{

        private String name;
        private String country;
        private String region;
        private float lat;
        private float lon;
        private String timezone_id;
        private String localtime;
        private int localtime_epoch;
        private float utc_offset;
    }

    @Data
    public class Current {
        private String observation_time;
        private int temperature;
        private int weather_code;
        private int wind_speed;
        private int wind_degree;
        private String wind_dir;
        private int pressure;
        private float precip;
        private int humidity;
        private int cloudcover;
        private int feelslike;
        private int uv_index;
        private int visibility;
        private String is_day;

    }


    @Data
    public class Error {
        private String code;
        private String type;
        private String info;

    }

}

