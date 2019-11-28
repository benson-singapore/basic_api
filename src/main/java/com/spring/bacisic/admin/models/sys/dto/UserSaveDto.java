package com.spring.bacisic.admin.models.sys.dto;

import com.spring.bacisic.admin.common.entity.BaseDto;
import com.spring.bacisic.admin.common.validate.ValidateSave;
import com.spring.bacisic.admin.models.sys.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

/**
 * 保存用户dto
 *
 * @author zhangby
 * @date 26/11/19 12:20 pm
 */
@Data
@Accessors(chain = true)
public class UserSaveDto extends BaseDto<User> {
    private String name;
    @NotBlank(message = "用户名不能为空", groups = {ValidateSave.class})
    private String loginName;
    private String mobile;
    private String email;
    private String photo;
    @NotBlank(message = "用户类型不能为空", groups = {ValidateSave.class})
    private String userType;
    @ApiModelProperty("多个角色用逗号间隔")
    private String roleId;
    @NotBlank(message = "部门不能为空", groups = {ValidateSave.class})
    private String officeId;
    @NotBlank(message = "密码不能为空", groups = {ValidateSave.class})
    private String password;

    @Override
    public User convert() {
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }
}
