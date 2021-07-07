package com.spring.testable.mock.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Jie.cai58@gmail.com
 */
@Data
public class MerchantVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Integer id;

    /**
     * 商家昵称
     */
    private String nickname;
    /**
     * 账号名称
     */
    private String account;

    /**
     * 账号名称Id
     */
    private String accountId;
    /**
     * 解冻时间
     */
    private Date freezeDeadTime;
    /**
     * 等级:默认=0,1=商户V1,2=商户V2,3=商户V3
     */
    private Integer level;
    /**
     * 状态:0=默认,1=已激活,2=取消激活,3=冻结
     */
    private Integer status;
    /**
     * 资金权限:0=默认,1=已开启,2=已关闭
     */
    private Integer authority;
    /**
     * 创建时间
     */
    private Date ctime;

}

