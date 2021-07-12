package com.spring.testable.mock.manage;

import com.alibaba.testable.core.annotation.MockMethod;
import com.spring.testable.mock.dao.HeroDao;
import com.spring.testable.mock.pojo.entity.Hero;

public class HeroManageMock {

    public static final String MOCK_ACCOUNT = "1024";

    @MockMethod
    private Hero heroinfo(HeroDao self, Long id){
        Hero hero = new Hero();
        hero.setHeroName("mock hero");
        hero.setId(id);
        hero.setSkill("mock skill");
        return hero;

    }
}
