package com.spring.bacisic.admin.models.sys.dto;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.spring.bacisic.admin.common.entity.BaseDto;
import com.spring.bacisic.admin.models.sys.entity.Menu;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * 菜单保存 dto
 *
 * @author zhangby
 * @date 27/11/19 1:35 pm
 */
@Data
@Accessors(chain = true)
public class MenuSaveDto extends BaseDto<Menu> {

    @ApiModelProperty(value = "父级编号")
    private String parentId;

    @NotBlank(message = "菜单名称不能为空")
    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "排序")
    private BigDecimal sort;

    @NotBlank(message = "链接不能为空")
    @ApiModelProperty(value = "链接")
    private String href;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "是否在菜单中显示")
    private String isShow;

    @ApiModelProperty(value = "ant 路由")
    private String component;

    @ApiModelProperty(value = "权限标识")
    private String permission;

    @Override
    public Menu convert() {
        Menu menu = new Menu();
        BeanUtils.copyProperties(this, menu);
        if (StrUtil.isBlank(menu.getParentId())) {
            menu.setParentId("0").setParentIds("0");
        } else {
            Menu parentMenu = new Menu().selectById(menu.getParentId());
            if (ObjectUtil.isNull(parentMenu)) {
                menu.setParentId(menu.getParentId()).setParentIds(menu.getParentId());
            } else {
                menu.setParentId(menu.getParentId())
                        .setParentIds(parentMenu.getParentIds() + parentMenu.getId());
            }
        }
        return menu;
    }
}
