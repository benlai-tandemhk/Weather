package com.zai.weather.proxy;

import com.zai.weather.dto.OpenWeatherMapResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
@SpringBootTest
public class OpenWeatherMapClientTest {

    @Autowired
    private OpenWeatherMapClient openWeatherMapClient;

    @Test
    public void testGetWeatherReturnNotNullObjectIfGivenCorrectLocationAndAppID() throws Exception {
        //given
        String location = "melbourne,AU";
        String appId = "your_api_key";

        //when
        OpenWeatherMapResponse result = openWeatherMapClient.getWeather(location, appId , null);

        //then
        assertNotNull(result.getMain());
        assertNotNull(result.getMain().getTemp());
        assertNotNull(result.getMain().getFeels_like());
        assertNotNull(result.getMain().getHumidity());
        assertNotNull(result.getMain().getPressure());
        assertNotNull(result.getMain().getTemp_max());
        assertNotNull(result.getMain().getTemp_min());

    }


    @Test
    public void testGetWeatherGivenCorrectLocationAndAppIDShouldReturnCode200() throws Exception {
        //given
        String location = "melbourne,AU";
        String appId = "your_api_key";

        //when
        OpenWeatherMapResponse result = openWeatherMapClient.getWeather(location, appId, null);


        //then
        int expectedCode = 200;
        assertEquals(200,  result.getCod() );

    }

    @Test
    public void testGetWeatherIfProvideWrongLocationThrowException404NotFound() {
        //given
        String location = "asdafaf";
        String appId = "your_api_key";

        //when
        Exception exception = assertThrows(Exception.class, ()->{
            OpenWeatherMapResponse result = openWeatherMapClient.getWeather(location, appId, null);

        });

        //then
        String expectedCode = "404";
        assertEquals(true,  exception.getMessage().contains(expectedCode) );

    }


    @Test
    public void testGetWeatherIfProvideWrongAppIDThrowException401Unauthorized() {
        //given
        String location = "asdafaf";
        String appId = "wrong_api_key";

        //when
        Exception exception = assertThrows(Exception.class, ()->{
            OpenWeatherMapResponse result = openWeatherMapClient.getWeather(location, appId, null);

        });
        //then
        String expectedCode = "401";
        assertEquals(true,  exception.getMessage().contains(expectedCode) );

    }
}
