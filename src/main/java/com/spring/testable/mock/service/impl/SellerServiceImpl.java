package com.spring.testable.mock.service.impl;

import com.spring.testable.mock.manage.CookerManage;
import com.spring.testable.mock.service.SellerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 被测类，会访问`CookerService`里的方法
 * Class to be tested, which will access methods in TargetService class
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Resource
    private CookerManage cookerService;

    @Override
    public String sellSandwich() {
        return cookerService.prepareSandwich();
    }

    @Override
    public String sellHamburger() {
        return cookerService.prepareHamburger();
    }
}
