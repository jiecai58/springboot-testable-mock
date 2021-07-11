package com.spring.testable.mock.dao;

import com.alibaba.testable.core.annotation.MockMethod;
import com.spring.testable.mock.pojo.entity.Merchant;

public class MerchantDaoMock {

    public static final String MOCK_ACCOUNT = "1024";

    @MockMethod
    private Merchant getMerchantInfoByAccount(MerchantDao self,String account) {
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
