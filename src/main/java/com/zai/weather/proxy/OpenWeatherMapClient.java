package com.zai.weather.proxy;

import com.zai.weather.dto.OpenWeatherMapResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(name = "OpenWeatherMap", url = "https://api.openweathermap.org")
public interface OpenWeatherMapClient {

    // https://api.openweathermap.org/data/2.5/weather?q=melbourne,AU&appid=6f95f2e361a1a8000831ddef0f421258


    @RequestMapping(method = GET, value = "/data/2.5/weather", produces = "application/json")
    OpenWeatherMapResponse getWeather(@RequestParam(value = "q") String city,
                                      @RequestParam(value = "appid") String appid,
                                      @RequestParam(value = "units" ) String units) throws Exception;


}
