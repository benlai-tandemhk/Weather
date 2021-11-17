package com.zai.weather.controller;

import com.zai.weather.dto.WeatherResponseDto;
import com.zai.weather.service.WeatherService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WeatherControllerTest {

    @InjectMocks
    private WeatherController weatherController;

    @Mock
    private WeatherService weatherService;


    @BeforeAll
    public void setUp()
    {
        MockitoAnnotations.openMocks(this);

    }


    @Test
    public void testGetWeather() throws Exception {

        String city = "melbourne";
        WeatherResponseDto weather = new WeatherResponseDto();
        Mockito.when(weatherService.getWeatherUsingOpenWeather(city)).thenReturn(weather);

        WeatherResponseDto testWeather = weatherController.getWeatherFromCity(city);
        assertEquals(weather, testWeather);
    }
}
