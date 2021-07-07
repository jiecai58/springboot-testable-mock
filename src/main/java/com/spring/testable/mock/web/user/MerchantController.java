package com.spring.testable.mock.web.user;


import com.spring.testable.mock.common.object.BaseResponse;
import com.spring.testable.mock.pojo.dto.UserDto;
import com.spring.testable.mock.pojo.vo.MerchantVo;
import com.spring.testable.mock.service.MerchantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *  商家Controller
 * @author Jie.cai58@gmail.com
 * @date 2019/10/15 17:06
 */
@RestController
@RequestMapping(value="/merchant")
@Api(value = "MerchantController")
public class MerchantController {

    @Resource
   private MerchantService merchantService;

    /**商家详情*/
    @PostMapping(value = "/detail")
    public BaseResponse<MerchantVo> detail(@RequestBody @Validated @ApiParam(name="merchant detail",value="json format") UserDto userDto){
         return new BaseResponse<>(merchantService.detail(userDto));
    }
}
