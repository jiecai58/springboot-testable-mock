package com.spring.testable.mock.example;

import cn.hutool.core.map.MapUtil;
import com.spring.testable.mock.common.http.feign.WeatherExample;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

import java.util.HashMap;
import java.util.Map;

public class CityWeather {

    private static final String API_URL = "http://t.weather.itboy.net";

    private static final String BEI_JING = "101010100";
    private static final String SHANG_HAI = "101020100";
    private static final String HE_FEI = "101220101";

    public static final Map<String, String> CITY_CODE = MapUtil.builder(new HashMap<String, String>())
            .put(BEI_JING, "北京市")
            .put(SHANG_HAI, "上海市")
            .put(HE_FEI, "合肥市")
            .build();

    private static WeatherApi weatherApi = Feign.builder()
            .encoder(new JacksonEncoder())
            .decoder(new JacksonDecoder())
            .target(WeatherApi.class, API_URL);

    public String queryShangHaiWeather() {
        WeatherExample.Response response = weatherApi.query(SHANG_HAI);
        return result(response);
    }

    private String queryHeFeiWeather() {
        WeatherExample.Response response = weatherApi.query(HE_FEI);
        return result(response);
    }

    public static String queryBeiJingWeather() {
        WeatherExample.Response response = weatherApi.query(BEI_JING);
        return result(response);
    }

    private static String result( WeatherExample.Response response){
        WeatherExample.Forecast forecast = response.getData().getForecast().get(0);
        return response.getCityInfo().getCity() + ": " + forecast.getYmd()+","+ forecast.getWeek()+","+forecast.getType() + ","+
                forecast.getLow() + "," +forecast.getHigh()+","
                + forecast.getFx() + forecast.getFl() +","+ forecast.getNotice();
    }

    public static void main(String[] args) {
        CityWeather cityWeather = new CityWeather();

        String shanghai = cityWeather.queryShangHaiWeather();
        String hefei = cityWeather.queryHeFeiWeather();
        String beijing = CityWeather.queryBeiJingWeather();

        System.out.println(shanghai);
        System.out.println(hefei);
        System.out.println(beijing);
    }
}
