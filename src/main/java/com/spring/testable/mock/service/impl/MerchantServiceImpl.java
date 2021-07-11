package com.spring.testable.mock.service.impl;

import com.spring.testable.mock.common.enums.ErrorCode;
import com.spring.testable.mock.common.exceptions.BusinessException;
import com.spring.testable.mock.manage.MerchantManage;
import com.spring.testable.mock.pojo.dto.UserDto;
import com.spring.testable.mock.pojo.entity.Merchant;
import com.spring.testable.mock.pojo.vo.MerchantVo;
import com.spring.testable.mock.service.MerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *  商家实现类
 * @author caijie
 * @date 2021/07/06 11:21
 */
@Service
public class MerchantServiceImpl implements MerchantService {

    @Resource
    private MerchantManage merchantManage;

    @Override
    public MerchantVo detail(UserDto userDto) {
        MerchantVo merchantVo = new MerchantVo();
        Merchant merchant = merchantManage.getMerchantInfoByAccount(userDto.getAccount());
        if(merchant == null ){
            throw new BusinessException(ErrorCode.ERROR_MERCHANT_NOT_EXIST);
        }
        BeanUtils.copyProperties(merchant, merchantVo);
        return merchantVo;
    }
}
