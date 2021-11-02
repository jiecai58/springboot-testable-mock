package com.spring.testable.mock.dao;

import com.spring.testable.mock.pojo.entity.Hero;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HeroDao extends BaseDao<Hero> {

    Hero heroinfo(Long id);
}
