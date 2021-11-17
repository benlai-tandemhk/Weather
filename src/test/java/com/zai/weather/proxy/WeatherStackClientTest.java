package com.zai.weather.proxy;

import com.zai.weather.dto.OpenWeatherMapResponse;
import com.zai.weather.dto.WeatherStackResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
public class WeatherStackClientTest {
    @InjectMocks
    private WeatherStackClient weatherStackClient;


    @Test
    public void testGetWeatherReturnNotNullObjectIfGivenCorrectLocationAndAppID() throws Exception {
        //given
        String location = "Melbourne";
        String access_key = "your_api_key";

        //when
        WeatherStackResponse result = weatherStackClient.getWeather(location, access_key);

        assertNotNull(result);

    }

}
