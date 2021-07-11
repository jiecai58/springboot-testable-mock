package com.spring.testable.mock.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.testable.mock.pojo.entity.Merchant;

/**
 * @author caijie
 */
public interface MerchantDao extends IService<Merchant> {

    Merchant getMerchantInfoByAccount(String account);

}
