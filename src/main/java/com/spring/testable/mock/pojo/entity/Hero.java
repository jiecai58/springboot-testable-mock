package com.spring.testable.mock.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tuhu.qpl.interceptor.encryption.annotation.SensitiveData;
import com.tuhu.qpl.interceptor.encryption.annotation.SensitiveField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author caijie
 */
@Data
@NoArgsConstructor
@SensitiveData
@Accessors(chain = true)
@TableName("hero")
public class Hero {

    @TableId(value = "id", type = IdType.AUTO)
    protected Long id;

    @SensitiveField
    @TableField(value = "hero_name", keepGlobalFormat = true)
    protected String heroName;

    @TableField(value = "skill", keepGlobalFormat = true)
    protected String skill;
}
