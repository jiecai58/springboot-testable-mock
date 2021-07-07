package com.spring.testable.mock.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.testable.mock.dao.MerchantDao;
import com.spring.testable.mock.mapper.MerchantMapper;
import com.spring.testable.mock.pojo.entity.Merchant;
import org.springframework.stereotype.Service;

/**
 * @author caijie
 */
@Service
public class MerchantDaoImpl extends ServiceImpl<MerchantMapper, Merchant> implements MerchantDao {

    @Override
    public Merchant getMerchantInfoByAccount(String account) {
        QueryWrapper<Merchant> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted",0).and(a->a.eq("account",account).or().eq("bak_account",account));
        Merchant result = getOne(queryWrapper);
        return result != null ? result : new Merchant();
    }
}
