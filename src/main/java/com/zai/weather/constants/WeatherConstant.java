package com.zai.weather.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class WeatherConstant{

    @Value("${weather.open-weather-key}")
    public String OPEN_WEATHER_API_KEY;


    @Value("${weather.weather-stack-key}")
    public String WEATHER_STACK_API_KEY;
}
