package com.spring.testable.mock.service;

import com.spring.testable.mock.pojo.dto.UserDto;
import com.spring.testable.mock.pojo.vo.MerchantVo;

/**
 * @author  caijie
 * @date 2021/07/06 11:07
 */

public interface MerchantService {

    MerchantVo detail(UserDto userDto);

}
