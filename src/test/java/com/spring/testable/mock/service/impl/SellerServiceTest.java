package com.spring.testable.mock.service.impl;

import com.spring.testable.mock.BaseTest;
import com.spring.testable.mock.service.SellerService;
import org.junit.Test;

import javax.annotation.Resource;

public class SellerServiceTest extends BaseTest {

    @Resource
    private SellerService sellerService;

    @Test
    public void should_sell_sandwich() {
        //"Fake-Sandwich-Cooker & Faked-Sandwich",
        sellerService.sellSandwich();
    }

    @Test
    public void should_sell_hamburger() {
       //"Real-Hamburger-Cooker & Faked-Hamburger"
        sellerService.sellHamburger();
    }

}
