package com.spring.bacisic.admin.models.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.spring.bacisic.admin.common.entity.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色菜单表 ${aaaa}
 * </p>
 *
 * @author zhangbiyu
 * @since 2019-11-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role_menu")
@ApiModel(value="RoleMenu对象", description="角色菜单表")
public class RoleMenu extends BaseEntity<RoleMenu> {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "角色编号")
    private String roleId;

    @ApiModelProperty(value = "菜单编号")
    private String menuId;


    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

}
