package com.spring.testable.mock.manage;

import com.alibaba.testable.core.annotation.MockMethod;
import com.spring.testable.mock.dao.HeroDao;
import com.spring.testable.mock.pojo.entity.Hero;

public class HeroManageMock {

    public static final Long MOCK_ACCOUNT = 1L;

    @MockMethod
    private Hero heroinfo(HeroDao self, Long id){

        if(MOCK_ACCOUNT.equals(id)){
            Hero hero = new Hero();
            hero.setHeroName("mock hero");
            hero.setId(id);
            hero.setSkill("mock skill");
            return hero;
        }
        return self.heroinfo(id);

    }
}
