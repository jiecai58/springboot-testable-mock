package com.spring.testable.mock.remote.feign;

import com.alibaba.testable.core.annotation.MockMethod;
import com.spring.testable.mock.common.http.feign.WeatherExample;

public class WeatherClientMock {

    public static final String MOCK_CITY_CODE = "1024";

    @MockMethod
    private WeatherExample.Response query(WeatherClient self, String cityCode) {
        if (MOCK_CITY_CODE.equals(cityCode)) {
            WeatherExample.Response response = new WeatherExample.Response();
            response.setCityInfo(new WeatherExample.CityInfo().setCity("mock城市"));
            response.setData(new WeatherExample.Data().setYesterday(
                    new WeatherExample.Forecast().setNotice("this is from mock")));
            return response;
        }
        return self.query(cityCode);
    }
}
