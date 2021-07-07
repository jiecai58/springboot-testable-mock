package com.spring.testable.mock.manage;

import com.alibaba.testable.core.annotation.MockMethod;
import com.alibaba.testable.core.model.MockScope;

/**
 * 复用Mock类 :进行类级别的Mock复用，只需将Mock容器定义为独立的类，然后在要使用它的测试类上通过@MockWith进行引用
 */
public class CookerManageMock {

    @MockMethod(targetClass = CookerManage.class)
    public static String hireSandwichCooker() {
        return "Fake-Sandwich-Cooker";
    }

    @MockMethod(targetClass = CookerManage.class, scope = MockScope.ASSOCIATED)
    public static String hireHamburgerCooker() {
        return "Fake-Hamburger-Cooker";
    }

    @MockMethod(targetClass = CookerManage.class)
    private String cookSandwich() {
        return "Faked-Sandwich";
    }

    @MockMethod(targetClass = CookerManage.class)
    private String cookHamburger() {
        return "Faked-Hamburger";
    }
}
