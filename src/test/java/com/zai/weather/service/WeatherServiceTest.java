package com.zai.weather.service;

import com.zai.weather.constants.WeatherConstant;
import com.zai.weather.dto.OpenWeatherMapResponse;
import com.zai.weather.dto.WeatherResponseDto;
import com.zai.weather.dto.WeatherStackResponse;
import com.zai.weather.proxy.OpenWeatherMapClient;
import com.zai.weather.proxy.WeatherStackClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WeatherServiceTest {

    @InjectMocks
    private WeatherService weatherService;

    @Mock
    private WeatherConstant weatherConstant;


    @Mock
    private OpenWeatherMapClient openWeatherMapClient;

    @Mock
    private WeatherStackClient weatherStackClient;

    @Mock
    private WeatherResponseDto mockWeather;

    @Mock
    private WeatherStackResponse weatherStackResponse;

    @Mock
    private OpenWeatherMapResponse openWeatherMapResponse;


    @Mock
    private ConcurrentHashMap<String, WeatherResponseDto> cacheForeverWeather = new ConcurrentHashMap<>();

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public void setUpValues(){
        mockWeather = new WeatherResponseDto();
        weatherStackResponse = new WeatherStackResponse();
        weatherStackResponse.setCurrent(weatherStackResponse.new Current());
        weatherStackResponse.getCurrent().setWind_speed(0);
        weatherStackResponse.getCurrent().setTemperature(0);
        openWeatherMapResponse = new OpenWeatherMapResponse();
        openWeatherMapResponse.setWind(openWeatherMapResponse.new Wind());
        openWeatherMapResponse.setMain(openWeatherMapResponse.new Main());
        openWeatherMapResponse.getMain().setTemp(0);
        openWeatherMapResponse.getWind().setSpeed(0);
        cacheForeverWeather.put("melbourne" , mockWeather);
    }

    @Test
    public void testGetWeatherUsingWeatherStack() throws Exception {
        Mockito.when(weatherStackClient.getWeather( anyString(), any())).thenReturn(weatherStackResponse);

        var result = weatherService.getWeatherUsingWeatherStack("melbourne");


        assertEquals(mockWeather, result);
    }


    @Test
    public void testGetWeatherUsingOpenWeatherMap() throws Exception {

        Mockito.when(openWeatherMapClient.getWeather( anyString(), any(),anyString())).thenReturn(openWeatherMapResponse);

        var result = weatherService.getWeatherUsingOpenWeather("melbourne");

        assertEquals(mockWeather, result);
    }

    @Test
    public void testGetWeatherUsingCached() throws Exception {
        Mockito.when(cacheForeverWeather.get("melbourne")).thenReturn(mockWeather);

        var result = weatherService.getCachedWeather("melbourne");

        assertEquals(mockWeather, result);
    }

}
