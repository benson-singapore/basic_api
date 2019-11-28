package com.spring.bacisic.admin.models.sys.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.bacisic.admin.models.sys.entity.Menu;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.function.Function;

/**
 * 初始化菜单dto
 *
 * @author zhangby
 * @date 28/11/19 12:26 pm
 */
@Data
@Accessors(chain = true)
public class MenuReloadDto {
    private String path;
    private String name;
    private String icon;
    @JsonIgnore
    private String id;
    @JsonIgnore
    private String parentId;

    private List<MenuReloadDto> children;

    /**
     * 初始化
     */
    public static Function<Menu, MenuReloadDto> init = menu ->
            new MenuReloadDto()
                    .setPath(menu.getHref())
                    .setName(menu.getName())
                    .setIcon(menu.getIcon())
                    .setId(menu.getId())
                    .setParentId(menu.getParentId());
}
