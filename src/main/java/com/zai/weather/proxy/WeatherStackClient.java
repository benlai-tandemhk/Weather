package com.zai.weather.proxy;

import com.zai.weather.dto.WeatherStackResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(name = "WeatherStack", url = "http://api.weatherstack.com")
public interface WeatherStackClient {

    @RequestMapping(method = GET, value = "/current", produces = "application/json")
    WeatherStackResponse getWeather(@RequestParam(value = "query") String location,
                                    @RequestParam(value = "access_key") String access_key) throws Exception;

}
