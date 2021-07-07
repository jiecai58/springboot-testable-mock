package com.spring.testable.mock.service.impl;

import com.alibaba.testable.core.annotation.MockWith;
import com.spring.testable.mock.BaseTest;
import com.spring.testable.mock.remote.feign.WeatherClientMock;
import com.spring.testable.mock.service.HeroService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * mock Feign 接口
 */

@MockWith(WeatherClientMock.class) // 测试类由于丢失与Mock容器的关联，需要@MockWith注解
public class HeroServiceImplTest extends BaseTest {

    private static final String SHANG_HAI = "101020100";
    public static final String MOCK_CITY_CODE = "1024";

    @Resource
    private HeroService heroService;

    //Mock方法的复用可以通过Mock容器类的继承来实现，父类中定义的所有Mock方法都会在子类中自然存在
    public static class Mock extends WeatherClientMock {

    }

    @Test
    public void storyTest() {
        String story = heroService.story(1L, MOCK_CITY_CODE);
        System.out.println(story);
    }

    @Test
    public void should_story() {
        String story = heroService.story(1L, SHANG_HAI);
        System.out.println(story);
    }

}
