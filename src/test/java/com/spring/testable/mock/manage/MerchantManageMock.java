package com.spring.testable.mock.manage;


import com.alibaba.testable.core.annotation.MockMethod;
import com.spring.testable.mock.dao.MerchantDao;
import com.spring.testable.mock.pojo.entity.Merchant;

/**
 *  对类进行mock，被mock方法只能类内方法调用
 * @author caijie
 * @date 2021/06/03 16:54
 */
public class MerchantManageMock {

    public static final String MOCK_ACCOUNT = "1024";

    @MockMethod
    private Merchant getMerchantInfoByAccount(MerchantDao self, String account) {
        if(account.equals(MOCK_ACCOUNT)){
            Merchant merchant = new Merchant();
            merchant.setId(1);
            merchant.setAccount("被mock的账号");
            merchant.setBakAccount("被mock备用账号");
            merchant.setAuthority(0);
            merchant.setLevel(0);
            return merchant;
        }
        return self.getMerchantInfoByAccount(account);
    }
 }
