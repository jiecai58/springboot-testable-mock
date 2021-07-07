package com.spring.testable.mock.service.impl;

import com.alibaba.testable.core.annotation.MockWith;
import com.alibaba.testable.processor.annotation.EnablePrivateAccess;
import com.spring.testable.mock.BaseTest;
import com.spring.testable.mock.pojo.dto.UserDto;
import com.spring.testable.mock.pojo.vo.MerchantVo;
import com.spring.testable.mock.service.MerchantService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * DAO 数据mock
 */
@MockWith
@EnablePrivateAccess  // 启用TestableMock的私有成员访问功能
public class MerchantServiceImplTest extends BaseTest {

    public static final String MOCK_ACCOUNT = "1024";

    @Resource
    private MerchantService merchantService;
    @Test
    public void detailTest() {
        UserDto userDto = new UserDto();
        userDto.setAccount(MOCK_ACCOUNT);
        MerchantVo detail = merchantService.detail(userDto);
        System.out.println(detail);
    }


}
