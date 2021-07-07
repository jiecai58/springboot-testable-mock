package com.spring.testable.mock.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *  商户表实体
 * @author caijie
 * @date 2021/07/06 11:18
 */
@Data
@TableName("merchant")
public class Merchant implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 账户名称
     */
    @TableField("account")
    private String account;

    /**
     * 备用账号名称
     */
    @TableField("bak_account")
    private String bakAccount;

    /**
     * 备用账号id
     */
    @TableField("bak_account_id")
    private String bakAccountId;
    /**
     * 商家昵称
     */
    private String nickname;

    /**
     * 解冻时间
     */
   @TableField("freeze_dead_time")
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
     * 备注
     */
    private String remark;
    /**
     * 是否删除 0=未删除 1=删除
     */
    @TableField("is_deleted")
    private Integer isDeleted;
    /**
     * 创建时间
     */
    @TableField("created_time")
    private Date createdTime;
    /**
     * 更新时间
     */
    @TableField("updated_time")
    private Date updatedTime;
}

