package com.spring.testable.mock.manage;

import com.spring.testable.mock.dao.MerchantDao;
import com.spring.testable.mock.pojo.entity.Merchant;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *  支付方式Manage接口实现类
 * @author caijie
 */
@Component
public class MerchantManage {

    @Resource
    private MerchantDao merchantDao;

    public Merchant getMerchantInfoByAccount(String account){
        //return merchantDao.getMerchantInfoByAccount(account);
        return getMerchantInfo(account);
    }

    private Merchant getMerchantInfo(String account){
        return merchantDao.getMerchantInfoByAccount(account);
    }

 }
