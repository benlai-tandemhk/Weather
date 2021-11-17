# Weather

Introduction
The purpose of this test is for you to demonstrate your strengths. Don't spend more than a few
hours working on this. You can code it in any language you are comfortable with

##Task
Create an HTTP Service that reports on Melbourne weather. This service will source its
information from either of the below providers:


1. weatherstack (primary):
curl "​http://api.weatherstack.com/current?access_key=YOUR_ACCESS_KEY&query=Melbourne​"

Documentation: ​https://weatherstack.com/documentation


2. OpenWeatherMap (failover):
curl
"http://api.openweathermap.org/data/2.5/weather?q=melbourne,AU&appid=2326504fb9b100bee
21400190e4dbe6d"

Documentation: ​https://openweathermap.org/current

##Specs
● The service can hard-code Melbourne as a city.


● The service should return a JSON payload with a unified response containing
temperature in degrees Celsius and wind speed.



● If one of the provider goes down, your service can quickly failover to a different provider
without affecting your customers.

```
The failover flow is call WeatherStack first,
as the api return 200 http status code,
so I will get the NullPointerException that make me failover to OpenWeatherMap, 
Then if I still get 404 / 401 Exception from OpenWeatherMap, 
I will failover to ConcurrentHaspMap function and return as lifelong Cache 
```

● Have scalability and reliability in mind when designing the solution.

```
There is Dto for the OpenWeatherMap response and WeatherStack response ,
for extend and using FeignClient as interface for 2 api for override 
```

● Weather results are fine to be cached for up to 3 seconds on the server in normal
behaviour to prevent hitting weather providers. Those results must be served as stale if
all weather providers are down.

```
Setup a Caffeine cache and config the expireAfterWrite for 3 secs so it will return cachable Weathers within 3 secs

There is ConcurrentHaspMap serve as a lifelong cache for return if both servers are down.

```

● The proposed solution should allow new developers to make changes to the code safely.

##Expected Output
Calling the service via curl (​http://localhost:8080/v1/weather?city=melboune​) should output the
following JSON payload
{
"wind_speed": 20,
"temperature_degrees": 29
}

##Assumtion 
wind_speed is kilometer / hour


## Environment Variables

This project is built on openjdk 11. Please set your JAVA_HOME to your JDK path, e.g.

```bash
  export JAVA_HOME=/Library/Java/JavaVirtualMachines/adoptopenjdk-11.jdk/Contents/Home
```

For the Api Key

```bash
  export OPEN_WEATHER_API_KEY={YOUR_KEY}
  export WEATHER_STACK_API_KEY={YOUR_KEY}
```


## Run Locally

Clone the project

```bash
  git clone https://github.com/benlai-tandemhk/Weather.git
```

Go to the project directory

```bash
  cd Weather
```

Install dependencies

```bash
  ./gradlew clean build
```

Start the server

```bash
  ./gradlew bootRun
```


## Running Tests

To run tests, run the following command

```bash
  ./gradlew test
```

## API Reference

see http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
