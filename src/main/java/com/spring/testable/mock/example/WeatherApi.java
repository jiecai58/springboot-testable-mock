package com.spring.testable.mock.example;

import com.spring.testable.mock.common.http.feign.WeatherExample;
import feign.Param;
import feign.RequestLine;

public interface WeatherApi {

    @RequestLine("GET /api/weather/city/{city_code}")
    WeatherExample.Response query(@Param("city_code") String cityCode);
}
