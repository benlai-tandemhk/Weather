package com.zai.weather.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zai.weather.constants.WeatherConstant;
import com.zai.weather.dto.OpenWeatherMapResponse;
import com.zai.weather.dto.WeatherResponseDto;
import com.zai.weather.dto.WeatherStackResponse;
import com.zai.weather.proxy.OpenWeatherMapClient;
import com.zai.weather.proxy.WeatherStackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
@CacheConfig(cacheNames = "weathers")
public class WeatherService {

    @Autowired
    private OpenWeatherMapClient openWeatherMapClient;

    @Autowired
    private WeatherStackClient weatherStackClient;

    @Autowired
    private WeatherConstant weatherConstant;

    @Autowired
    private CacheManager cacheManager;

    //use for create a lifelong cache for holding up the latest value if the service is unavailable
    private ConcurrentHashMap<String, WeatherResponseDto> cacheForeverWeather = new ConcurrentHashMap<>();


    @Cacheable(key = "#city", unless = "#result == null")
    @HystrixCommand(fallbackMethod = "getWeatherUsingOpenWeather")
    public WeatherResponseDto getWeatherUsingWeatherStack(String city) throws Exception {

        System.out.println("fallbackMethod to getWeatherUsingWeatherStack");
        WeatherStackResponse result = weatherStackClient.getWeather(city, weatherConstant.WEATHER_STACK_API_KEY);
        WeatherResponseDto response = new WeatherResponseDto();

        System.out.println(result.toString());

        //here will throw exception if the getCurrent is null if using wrong city or wrong api key
        response.setWind_speed(Math.round(result.getCurrent().getWind_speed()));
        response.setTemperature_degrees(Math.round((result.getCurrent().getTemperature())));

        cacheForeverWeather.put(city, response);
        return response;

    }

    @CachePut(key = "#city", unless = "#result == null")
    @HystrixCommand(fallbackMethod = "getCachedWeather")
    public WeatherResponseDto getWeatherUsingOpenWeather(String city) throws Exception {

        //add country code just for this OpenWeather Api
        String c = city+",AU";

        OpenWeatherMapResponse result = openWeatherMapClient.getWeather(c, weatherConstant.OPEN_WEATHER_API_KEY, "metric");

        WeatherResponseDto response = new WeatherResponseDto();

        response.setWind_speed(Math.round(result.getWind().getSpeed()));
        response.setTemperature_degrees(Math.round((result.getMain().getTemp())));

        cacheForeverWeather.put(city, response);
        return response;
    }



    public WeatherResponseDto getCachedWeather(String city) throws Exception {
        System.out.println("fallbackMethod to getCachedWeather");
        if (cacheForeverWeather.get(city) != null) {
            return cacheForeverWeather.get(city);
        } else {
            //as all weather services are unavailable, so return HttpStatus.SERVICE_UNAVAILABLE
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "All weather services are down!");
        }
    }
}
