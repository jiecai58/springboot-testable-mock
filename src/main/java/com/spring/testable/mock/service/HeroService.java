package com.spring.testable.mock.service;

import com.spring.testable.mock.pojo.entity.Hero;

/**
 * @author caijie
 */
public interface HeroService {

    String story(Long id, String cityCode);

    boolean insert(Hero hero);

    boolean update(Hero hero);

}
