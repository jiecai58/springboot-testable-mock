package com.spring.testable.mock.example;

import cn.hutool.core.map.MapUtil;
import com.spring.testable.mock.common.http.feign.WeatherExample;
import feign.Feign;
import feign.codec.Decoder;
import feign.codec.Encoder;

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
            .encoder(new Encoder.Default())
            .decoder(new Decoder.Default())
            .target(WeatherApi.class, API_URL);

    public String queryShangHaiWeather() {
        WeatherExample.Response response = weatherApi.query(SHANG_HAI);
        return response.getCityInfo().getCity() + ": " + response.getData().getYesterday().getNotice();
    }

    private String queryHeFeiWeather() {
        WeatherExample.Response response = weatherApi.query(HE_FEI);
        return response.getCityInfo().getCity() + ": " + response.getData().getYesterday().getNotice();
    }

    public static String queryBeiJingWeather() {
        WeatherExample.Response response = weatherApi.query(BEI_JING);
        return response.getCityInfo().getCity() + ": " + response.getData().getYesterday().getNotice();
    }
}
