package com.spring.testable.mock.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author caijie
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("hero")
public class Hero {

    @TableId("id")
    protected Long id;

    @TableField(value = "hero_name", keepGlobalFormat = true)
    protected String heroName;

    @TableField(value = "skill", keepGlobalFormat = true)
    protected String skill;
}
