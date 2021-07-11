package com.spring.testable.mock.dao;

import com.alibaba.testable.core.annotation.MockMethod;
import com.spring.testable.mock.pojo.entity.Hero;

public class HeroDaoMock {

    @MockMethod
    private Hero heroinfo(HeroDao self, Long id){
        Hero hero = new Hero();
        hero.setHeroName("mock hero");
        hero.setId(id);
        hero.setSkill("mock skill");
        return hero;

    }

}
