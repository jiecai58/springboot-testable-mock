package com.spring.testable.mock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.testable.mock.pojo.entity.Hero;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface HeroMapper extends BaseMapper<Hero> {

}
