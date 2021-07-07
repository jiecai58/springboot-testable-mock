package com.spring.testable.mock.manage;

import com.spring.testable.mock.dao.HeroDao;
import com.spring.testable.mock.pojo.entity.Hero;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author caijie
 */
@Component
public class HeroManage {

    @Resource
    private HeroDao heroDao;

    public Hero getHeroInfoById(Long id){
        return heroDao.heroinfo(id);
    }

}
