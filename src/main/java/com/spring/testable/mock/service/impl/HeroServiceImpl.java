package com.spring.testable.mock.service.impl;

import com.spring.testable.mock.common.http.feign.WeatherExample;
import com.spring.testable.mock.manage.HeroManage;
import com.spring.testable.mock.pojo.entity.Hero;
import com.spring.testable.mock.remote.feign.WeatherClient;
import com.spring.testable.mock.service.HeroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author caijie
 */
@Slf4j
@Service
public class HeroServiceImpl implements HeroService {

    @Resource
    private WeatherClient weatherClient;

    @Resource
    private HeroManage heroManage;

    @Override
    public String story(Long id, String cityCode) {
        Hero hero = heroManage.getHeroInfoById(id);
        if (hero == null) {
            return String.format("heroId为%d不存在", id);
        }
        WeatherExample.Response weather = weatherClient.query(cityCode);

        StringBuilder sb = new StringBuilder();
        sb.append("人物：").append(hero.getHeroName()).append("\n");
        sb.append("技能：").append(hero.getSkill()).append("\n");
        sb.append("地点：").append(weather.getCityInfo().getCity()).append("\n");
        sb.append("天气：").append(weather.getData().getYesterday().getNotice());

        return sb.toString();
    }
}
