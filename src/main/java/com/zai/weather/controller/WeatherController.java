package com.zai.weather.controller;


import com.zai.weather.dto.WeatherResponseDto;
import com.zai.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("")
    public WeatherResponseDto getWeatherFromCity(@RequestParam (value = "city") String city) throws Exception
    {
        return weatherService.getWeatherUsingWeatherStack(city);
    }

}
