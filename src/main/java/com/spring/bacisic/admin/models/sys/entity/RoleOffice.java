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
 * 角色职位表 ${aaaa}
 * </p>
 *
 * @author zhangbiyu
 * @since 2019-11-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role_office")
@ApiModel(value="RoleOffice对象", description="角色职位表")
public class RoleOffice extends BaseEntity<RoleOffice> {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "角色编号")
    private String roleId;

    @ApiModelProperty(value = "机构编号")
    private String officeId;


    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

}
