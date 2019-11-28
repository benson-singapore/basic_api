package com.spring.bacisic.admin.models.sys.entity.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 字典类型枚举
 *
 * @author zhangby
 * @date 1/10/19 11:00 am
 */
public enum DictTypeEnum implements IEnum<String> {
    /**
     * 角色类型
     */
    roleType("role_type"),

    ;

    /**
     * 字典类型
     */
    private String type;

    DictTypeEnum(String type) {
        this.type = type;
    }


    @Override
    public String getValue() {
        return this.type;
    }
}