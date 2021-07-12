package com.spring.testable.mock.service.impl;

import com.alibaba.testable.core.annotation.MockWith;
import com.spring.testable.mock.BaseTest;
import com.spring.testable.mock.service.SellerService;
import org.junit.Test;

import javax.annotation.Resource;

import static com.alibaba.testable.core.matcher.InvokeVerifier.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MockWith
public class SellerServiceTest extends BaseTest {

    @Resource
    private SellerService sellerService;

    @Test
    public void should_sell_sandwich() {
        assertEquals("Fake-Sandwich-Cooker & Faked-Sandwich", sellerService.sellSandwich());
        verify("hireSandwichCooker").withTimes(1);
    }

    @Test
    public void should_sell_hamburger() {
        assertEquals("Real-Hamburger-Cooker & Faked-Hamburger", sellerService.sellHamburger());
        verify("cookHamburger").withTimes(1);
    }

}
