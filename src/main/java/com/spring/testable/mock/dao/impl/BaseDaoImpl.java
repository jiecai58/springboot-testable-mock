package com.spring.testable.mock.dao.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.testable.mock.dao.BaseDao;

@SuppressWarnings("unchecked")
public class BaseDaoImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseDao<T> {

}
