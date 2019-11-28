package com.spring.bacisic.admin.models.sys.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;

import com.spring.bacisic.admin.common.annotation.EnumFormat;
import java.io.Serializable;

import com.spring.bacisic.admin.common.entity.BaseEntity;
import com.spring.bacisic.admin.models.sys.entity.enums.UserLoginFlagEnum;
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
@TableName("sys_user")
@ApiModel(value="User对象", description="用户表")
public class User extends BaseEntity<User> {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "编号")
    private String id;

    @ApiModelProperty(value = "归属公司")
    private String companyId;

    @ApiModelProperty(value = "归属部门")
    private String officeId;

    @ApiModelProperty(value = "登录名")
    private String loginName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "工号")
    private String no;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "手机")
    private String mobile;

    @ApiModelProperty(value = "用户类型")
    private String userType;

    @ApiModelProperty(value = "用户头像")
    private String photo;

    @ApiModelProperty(value = "最后登陆IP")
    private String loginIp;

    @ApiModelProperty(value = "最后登陆时间",example = "2019-11-22 00:00:00")
    private Date loginDate;

    @EnumFormat
    @ApiModelProperty(value = "登录状态 : 0 正常，1 异常")
    private UserLoginFlagEnum loginFlag;

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
