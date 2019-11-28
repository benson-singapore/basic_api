package com.spring.bacisic.admin.models.sys.entity.enums;

import com.spring.bacisic.admin.common.enums.BaseEnum;

/**
 * 用户登录状态枚举
 *
 * @author zhangby
 * @date 22/11/19 5:24 pm
 */
public enum UserLoginFlagEnum implements BaseEnum<UserLoginFlagEnum> {
    /** 登录状态 : 0 正常，1 异常 */
    /**
     * 正常
     */
    normal("正常", "0"),
    /**
     * 禁用
     */
    freeze("禁用", "1");

    private String label;
    private String value;

    UserLoginFlagEnum(String label, String value) {
        this.label = label;
        this.value = value;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getValue() {
        return value;
    }

}
