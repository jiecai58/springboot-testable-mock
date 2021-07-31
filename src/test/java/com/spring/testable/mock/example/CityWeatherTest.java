package com.spring.testable.mock.example;

import com.alibaba.testable.core.tool.PrivateAccessor;
import com.alibaba.testable.processor.annotation.EnablePrivateAccess;
import com.spring.testable.mock.BaseTest;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@EnablePrivateAccess
public class CityWeatherTest extends BaseTest {

    public static class Mock {

/*        @MockMethod(targetMethod = "query")
        public WeatherExample.Response query(WeatherApi self, String cityCode) {
            WeatherExample.Response response = new WeatherExample.Response();
            // mock天气接口调用返回的结果
            response.setCityInfo(new WeatherExample.CityInfo().setCity(
                    CityWeather.CITY_CODE.getOrDefault(cityCode, cityCode)));
            ArrayList<WeatherExample.Forecast> forecasts = new ArrayList<>();
            WeatherExample.Forecast forecast = new WeatherExample.Forecast();
            forecast.setFl("");
            forecast.setType("");
            forecast.setLow("");
            forecast.setHigh("");
            forecast.setFx("");
            forecast.setNotice("this is from mock");
            forecasts.add(forecast);
            response.setData(new WeatherExample.Data().setForecast(forecasts));
            return response;
        }*/
    }

    CityWeather cityWeather = new CityWeather();

    /**
     * 测试 public方法调用
     */
    @Test
    public void test_public() {

        String shanghai = cityWeather.queryShangHaiWeather();
        assertEquals("上海市: this is from mock", shanghai);
    }

    /**
     * 测试 private方法调用
     */
    @Test
    public void test_private() {

        String hefei = (String) PrivateAccessor.invoke(cityWeather, "queryHeFeiWeather");
        assertEquals("合肥市: this is from mock", hefei);
    }

    /**
     * 测试 静态方法调用
     */
    @Test
    public void test_static() {

        String beijing = CityWeather.queryBeiJingWeather();
        assertEquals("北京市: this is from mock", beijing);
    }

}
