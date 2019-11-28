package com.spring.bacisic.admin.models.sys.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;

import com.spring.bacisic.admin.common.annotation.EnumFormat;
import com.spring.bacisic.admin.common.entity.BaseEntity;
import java.io.Serializable;

import com.spring.bacisic.admin.models.sys.entity.enums.RoleTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 'sys.statements_with_temp_tables' is not BASE TABLE ${aaaa}
 * </p>
 *
 * @author zhangbiyu
 * @since 2019-11-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role")
@ApiModel(value="Role对象", description="'sys.statements_with_temp_tables' is not BASE TABLE")
public class Role extends BaseEntity<Role> {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "编号")
    private String id;

    @ApiModelProperty(value = "归属机构")
    private String officeId;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "英文名称")
    private String enname;

    @ApiModelProperty(value = "角色类型")
    private String roleType;

    @ApiModelProperty(value = "数据范围")
    private String dataScope;

    @ApiModelProperty(value = "是否系统数据")
    private String isSys;

    @ApiModelProperty(value = "是否可用")
    private String useable;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "创建时间",example = "2019-11-22 00:00:00")
    private Date createDate;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "更新时间",example = "2019-11-22 00:00:00")
    private Date updateDate;

    @ApiModelProperty(value = "备注信息")
    private String remarks;

    @TableLogic
    @ApiModelProperty(value = "删除标记")
    private String delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
