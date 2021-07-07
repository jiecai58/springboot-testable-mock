package com.spring.testable.mock.remote.feign;

import com.spring.testable.mock.common.http.feign.WeatherExample;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "whether", url = "http://t.weather.itboy.net")
public interface WeatherClient {

    @GetMapping(value = "/api/weather/city/{code}")
    WeatherExample.Response query(@PathVariable("code") String cityCode);
}
