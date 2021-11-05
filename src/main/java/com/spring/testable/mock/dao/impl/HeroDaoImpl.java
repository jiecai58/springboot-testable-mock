package com.spring.testable.mock.dao.impl;

import com.spring.testable.mock.dao.HeroDao;
import com.spring.testable.mock.mapper.HeroMapper;
import com.spring.testable.mock.pojo.entity.Hero;
import org.springframework.stereotype.Service;

/**
 * @author caijie
 */
@Service
public class HeroDaoImpl extends BaseDaoImpl<HeroMapper, Hero> implements HeroDao {

    @Override
    public Hero heroinfo(Long id){
        Hero hero = getById(id);
        if(hero == null){
            return new Hero();
        }
        return hero;
    }

    @Override
    public boolean insert(Hero hero){
        return save(hero);
    }

    @Override
    public boolean update(Hero hero){
        return updateById(hero);
    }

}
