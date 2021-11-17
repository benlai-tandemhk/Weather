package com.zai.weather.dto;

import lombok.Data;

@Data
public class OpenWeatherMapResponse {

    private Wind wind;

    private Main main;

    private String name;

    private int cod;

    @Data
    public class Wind {
        private float speed;
        private int deg;
        private float gust;
    }

    @Data
    public class Main {
        private float temp;
        private float feels_like;
        private float temp_min;
        private float temp_max;
        private int pressure;
        private int humidity;
    }
}

