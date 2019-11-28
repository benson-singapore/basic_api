package com.spring.bacisic.admin.models.sys.entity.enums;

import com.spring.bacisic.admin.common.enums.BaseEnum;

/**
 * 角色类型枚举
 *
 * @author zhangby
 * @date 23/11/19 1:52 pm
 */
public enum RoleTypeEnum implements BaseEnum {
    /**
     * 角色类型：0.系统管理员，
     */
    admin("系统管理员", "0"),
    /**
     * 角色类型：1.运维人员，
     */
    operation("运维人员", "1"),
    /**
     * 角色类型：2.开发人员，
     */
    development("开发人员", "2"),
    /**
     * 角色类型：3.普通用户，
     */
    customer("普通用户", "3"),
    /**
     * 角色类型：4.其他，
     */
    other("其他", "4");

    private String label;
    private String value;

    RoleTypeEnum(String label, String value) {
        this.label = label;
        this.value = value;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
