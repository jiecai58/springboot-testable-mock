package com.spring.testable.mock.manage;

import org.springframework.stereotype.Component;

/**
 * 目标类，此类中的一些调用将会被Mock掉
 * Target class, some invocations inside this class will be mocked
 */
@Component
public class CookerManage {

    private static String hireSandwichCooker() {
        return "Real-Sandwich-Cooker";
    }

    private static String hireHamburgerCooker() {
        return "Real-Hamburger-Cooker";
    }

    private String cookSandwich() {
        return "Real-Sandwich";
    }

    public String cookHamburger() {
        return "Real-Hamburger";
    }

    public String prepareSandwich() {
        return hireSandwichCooker() + " & " + cookSandwich();
    }

    public String prepareHamburger() {
        return hireHamburgerCooker() + " & " + cookHamburger();
    }

}
