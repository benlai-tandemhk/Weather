package com.zai.weather.dto;

import lombok.Data;

@Data
public class WeatherResponseDto {

    private int temperature_degrees;
    private int wind_speed;

}
