package com.spring.bacisic.admin.models.sys.controller;


import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.spring.bacisic.admin.common.constants.Constants;
import com.spring.bacisic.admin.common.entity.ResultPoJo;
import com.spring.bacisic.admin.common.util.CommonUtil;
import com.spring.bacisic.admin.common.util.UserUtil;
import com.spring.bacisic.admin.models.sys.dto.MenuReloadDto;
import com.spring.bacisic.admin.models.sys.dto.MenuSaveDto;
import com.spring.bacisic.admin.models.sys.entity.Menu;
import com.spring.bacisic.admin.models.sys.entity.Role;
import com.spring.bacisic.admin.models.sys.entity.RoleMenu;
import com.spring.bacisic.admin.models.sys.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author zhangbiyu
 * @since 2019-11-27
 */
@RestController
@RequestMapping("/sys/menu")
@Api(tags = "菜单管理")
public class MenuController {

    @Autowired
    IMenuService menuService;

    /**
     * 菜单列表查询
     *
     * @return ResultPoJo
     */
    @GetMapping("")
    @ApiOperation(value = "列表查询", notes = "列表查询", produces = "application/json")
    public ResultPoJo<List<Menu>> getMenuList4Page(String keyword) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<Menu>().orderByAsc(Menu::getSort).orderByDesc(Menu::getCreateDate);
        CommonUtil.emptyStr(keyword).ifPresent(key -> queryWrapper.and(andQw ->
                andQw.or(orQw -> orQw.like(Menu::getName, key))
                        .or(orQw -> orQw.like(Menu::getHref, key))
        ));
        // 菜单查询
        List<Menu> menuList = menuService.list(queryWrapper);
        List<Menu> rsList = menuList;
        if (StrUtil.isBlank(keyword)) {
            menuList.forEach(menu -> {
                if (!"0".equals(menu.getParentId())) {
                    menuList.stream().filter(mu -> mu.getId().equals(menu.getParentId())).findFirst()
                            .ifPresent(parentMenu -> {
                                List<Menu> children = parentMenu.getChildren();
                                if (ObjectUtil.isNull(children)) {
                                    children = Lists.newArrayList();
                                }
                                children.add(menu);
                                parentMenu.setChildren(children);
                            });
                }
            });
            rsList = menuList.stream().filter(menu -> "0".equals(menu.getParentId())).collect(Collectors.toList());
        }
        return ResultPoJo.ok(rsList);
    }

    /**
     * 根据id查询菜单
     *
     * @return ResultPoJo
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "查询菜单", notes = "根据id查询菜单", produces = "application/json")
    public ResultPoJo<Menu> getMenu4Id(@PathVariable String id) {
        Menu menu = menuService.getById(id);
        if (!"0".equals(menu.getParentId())) {
            Menu parentMenu = new Menu().selectById(menu.getParentId());
            menu.set("parentName", parentMenu.getName());
        }
        return ResultPoJo.ok(menu);
    }

    /**
     * 菜单保存
     *
     * @return ResultPoJo
     */
    @PostMapping("")
    @ApiOperation(value = "菜单保存", notes = "菜单保存", produces = "application/json")
    public ResultPoJo saveMenu(@Validated @RequestBody MenuSaveDto menuSaveDto) {
        Menu menu = menuSaveDto.convert();
        menu.preInsert();
        menuService.save(menu);
        // 保存角色菜单，默认管理员有所有菜单
        new RoleMenu().setMenuId(menu.getId()).setMenuId(Constants.ROLE_ADMIN_ID).insert();
        return ResultPoJo.ok();
    }

    /**
     * 菜单更新
     *
     * @return ResultPoJo
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "菜单更新", notes = "菜单更新", produces = "application/json")
    public ResultPoJo updateMenu(@PathVariable String id, @Validated @RequestBody MenuSaveDto menuSaveDto) {
        Menu menu = menuSaveDto.convert().setId(id);
        menu.preUpdate();
        menuService.updateById(menu);
        return ResultPoJo.ok();
    }

    /**
     * 删除菜单
     *
     * @return ResultPoJo
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除菜单", notes = "删除菜单", produces = "application/json")
    public ResultPoJo deleteMenu(@PathVariable String id) {
        menuService.removeById(id);
        //删除菜单角色关联信息
        new RoleMenu().delete(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getMenuId, id));
        return ResultPoJo.ok();
    }

    /**
     * 获取最大排序值
     * @param parentId parentId
     * @return ResultPoJo
     */
    @GetMapping("/max/sort")
    @ApiOperation(value = "获取最大排序值", notes = "获取最大排序值", produces = "application/json")
    public ResultPoJo getMaxSort(@RequestParam(value = "parentId", required = false) String parentId) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<Menu>()
                .eq(Menu::getParentId, CommonUtil.emptyStr(parentId).orElse(Constants.SYS_PARENT_ID));
        BigDecimal maxSort = menuService.list(queryWrapper).stream()
                .map(Menu::getSort)
                .sorted((m1, m2) -> -m1.compareTo(m2))
                .findFirst().orElse(BigDecimal.ZERO);
        return ResultPoJo.ok(Dict.create().set("maxSort", maxSort.add(new BigDecimal(10))));
    }

    /**
     * 根据角色获取菜单
     * @param roleId id
     * @return ResultPoJo
     */
    @GetMapping("/get/for/role/{roleId}")
    @ApiOperation(value = "根据角色获取菜单", notes = "根据角色获取菜单", produces = "application/json")
    public ResultPoJo getMenu4Role(@PathVariable String roleId) {
        List<RoleMenu> roleMenus = new RoleMenu().selectList(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, roleId));
        return ResultPoJo.ok(roleMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toList()));
    }

    /**
     * 保存角色菜单
     * @param roleId roleId
     * @param menuIds menuIds
     * @return ResultPoJo
     */
    @PostMapping("/save/menu/role/{roleId}")
    @ApiOperation(value = "保存角色菜单", notes = "保存角色菜单", produces = "application/json")
    public ResultPoJo saveRoleMenu(@PathVariable String roleId, String menuIds) {
        //删除角色菜单
        new RoleMenu().delete(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, roleId));
        //保存角色菜单
        Stream.of(menuIds.split(",")).forEach(menuId -> {
            new RoleMenu().setMenuId(menuId).setRoleId(roleId).insert();
        });
        return ResultPoJo.ok();
    }

    /**
     * 初始化菜单
     * @return
     */
    @GetMapping("/get/for/user")
    @ApiOperation(value = "初始化菜单", notes = "初始化菜单", produces = "application/json")
    public ResultPoJo<List<MenuReloadDto>> getMenu4User() {
        List<MenuReloadDto> reloadDtos = Lists.newArrayList();
        // 根据用户查询菜单
        List<MenuReloadDto> menus = CommonUtil.convers(menuService.getMenu4User(UserUtil.getCurrentUserId()), MenuReloadDto.init);
        // 树级转换
        menus.forEach(menu -> {

        });
        return ResultPoJo.ok();
    }
}

