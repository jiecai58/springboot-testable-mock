package com.spring.testable.mock.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.testable.mock.pojo.entity.Merchant;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *  商户表 Mapper 接口
 * @author caijie
 */

@Mapper
public interface MerchantMapper extends BaseMapper<Merchant> {

 List<Merchant> all();

}
