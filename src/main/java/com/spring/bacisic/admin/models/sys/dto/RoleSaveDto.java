package com.spring.bacisic.admin.models.sys.dto;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.spring.bacisic.admin.common.annotation.EnumFormat;
import com.spring.bacisic.admin.common.entity.BaseDto;
import com.spring.bacisic.admin.models.sys.entity.Role;
import com.spring.bacisic.admin.models.sys.entity.enums.RoleTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

/**
 * 角色保存dto
 *
 * @author zhangby
 * @date 27/11/19 5:13 pm
 */
@Data
@Accessors(chain = true)
public class RoleSaveDto extends BaseDto<Role> {

    @NotBlank(message = "角色名称不能为空")
    @ApiModelProperty(value = "角色名称")
    private String name;

    @NotBlank(message = "英文名称不能为空")
    @ApiModelProperty(value = "英文名称")
    private String enname;

    @NotBlank(message = "请选择角色类型")
    @ApiModelProperty(value = "角色类型")
    private String roleType;

    @ApiModelProperty(value = "是否可用")
    private String useable;

    @Override
    public Role convert() {
        Role role = new Role();
        BeanUtils.copyProperties(this,role);
        return role;
    }
}
