package com.spring.testable.mock.service;


import com.spring.testable.mock.common.http.feign.WeatherExample;
import feign.RequestLine;
import org.apache.ibatis.annotations.Param;

/**
 * @author caijie
 */
public interface WeatherApi {

    @RequestLine("GET /api/weather/city/{city_code}")
    WeatherExample.Response query(@Param("city_code") String cityCode);
}
