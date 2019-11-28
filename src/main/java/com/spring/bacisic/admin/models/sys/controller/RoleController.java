package com.spring.bacisic.admin.models.sys.controller;


import cn.hutool.core.lang.Dict;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import com.spring.bacisic.admin.common.constants.Constants;
import com.spring.bacisic.admin.common.entity.Pagination;
import com.spring.bacisic.admin.common.entity.ResultPoJo;
import com.spring.bacisic.admin.common.exception.MyBaselogicException;
import com.spring.bacisic.admin.common.util.CommonUtil;
import com.spring.bacisic.admin.models.sys.dto.RoleSaveDto;
import com.spring.bacisic.admin.models.sys.entity.Role;
import com.spring.bacisic.admin.models.sys.entity.User;
import com.spring.bacisic.admin.models.sys.entity.UserRole;
import com.spring.bacisic.admin.models.sys.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 'sys.statements_with_temp_tables' is not BASE TABLE 前端控制器
 * </p>
 *
 * @author zhangbiyu
 * @since 2019-11-22
 */
@RestController
@RequestMapping("/sys/role")
@Api(tags = "角色管理")
public class RoleController {

    @Autowired
    IRoleService roleService;

    /**
     * 查询角色
     *
     * @return ResultPoJo
     */
    @GetMapping("")
    @ApiOperation(value = "查询角色列表", notes = "", produces = "application/json")
    public ResultPoJo<IPage<Role>> queryRoleList4Page(Pagination pagination, String keyword) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<Role>().orderByAsc(Role::getCreateDate);
        CommonUtil.emptyStr(keyword).ifPresent(key -> {
            queryWrapper.and(andQw -> {
                andQw.or(orQw -> orQw.like(Role::getName, key))
                        .or(orQw -> orQw.like(Role::getEnname, key));
            });
        });
        IPage page = roleService.page(pagination.page(), queryWrapper);
        CommonUtil.convers(page.getRecords(), roleService.preInit());
        return ResultPoJo.ok(page);
    }

    /**
     * 查询角色详情
     *
     * @return ResultPoJo
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "查询角色详情", notes = "查询角色详情", produces = "application/json")
    public ResultPoJo<Role> getRoleById(@PathVariable String id) {
        Role role = roleService.getById(id);
        return ResultPoJo.ok(roleService.preInit().apply(role));
    }

    /**
     * 保存角色
     *
     * @return
     */
    @PostMapping("")
    @ApiOperation(value = "保存角色", notes = "保存角色", produces = "application/json")
    public ResultPoJo saveRole(@Validated @RequestBody RoleSaveDto roleSaveDto) {
        // 验证英文名不能重复
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<Role>().eq(Role::getEnname, roleSaveDto.getEnname());
        List<Role> list = roleService.list(queryWrapper);
        if (!list.isEmpty()) {
            throw new MyBaselogicException("112");
        }
        Role role = roleSaveDto.convert();
        role.preInsert();
        roleService.save(role);
        return ResultPoJo.ok();
    }

    /**
     * 角色更新
     *
     * @return
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "更新角色", notes = "更新角色", produces = "application/json")
    public ResultPoJo updateRole(@PathVariable String id, @Validated @RequestBody RoleSaveDto roleSaveDto) {
        // 验证英文名不能重复
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<Role>()
                .eq(Role::getEnname, roleSaveDto.getEnname())
                .ne(Role::getId, id);
        List<Role> list = roleService.list(queryWrapper);
        if (!list.isEmpty()) {
            throw new MyBaselogicException("112");
        }
        Role role = roleSaveDto.convert().setId(id);
        role.preUpdate();
        roleService.updateById(role);
        return ResultPoJo.ok();
    }

    /**
     * 删除角色
     *
     * @param id id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除角色", notes = "删除角色", produces = "application/json")
    public ResultPoJo deleteRole(@PathVariable String id) {
        roleService.removeByIds(Lists.newArrayList(id.split(",")));
        return ResultPoJo.ok();
    }

    /**
     * 查询角色选择下拉框
     *
     * @return
     */
    @GetMapping("/select/data")
    @ApiOperation(value = "查询角色选择下拉框", notes = "查询角色选择下拉框", produces = "application/json")
    public ResultPoJo<Dict> getRoleSelectData() {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<Role>()
                .orderByAsc(Role::getCreateDate);
        List<Dict> rsList = CommonUtil.convers(roleService.list(queryWrapper), role -> Dict.create()
                .set("label", role.getName())
                .set("value", role.getId())
        );
        return ResultPoJo.ok(rsList);
    }

    /**
     * 获取用户授权角色
     *
     * @return ResultPoJo
     */
    @GetMapping("/get/user/auth/{roleId}")
    @ApiOperation(value = "获取用户授权角色", notes = "获取用户授权角色", produces = "application/json")
    public ResultPoJo getUser4RoleId(@PathVariable String roleId) {
        // 获取选中的记录
        List<UserRole> userRoles = new UserRole().selectList(new LambdaQueryWrapper<UserRole>().eq(UserRole::getRoleId, roleId));
        List<String> checked = userRoles.stream().map(UserRole::getUserId).collect(Collectors.toList());
        // 获取用户记录
        List<User> users = new User().selectList(new LambdaQueryWrapper<User>().ne(User::getId, Constants.USER_ADMIN_ID));
        List<Dict> userList = users.stream().map(user -> Dict.create()
                .set("key", user.getId())
                .set("name", user.getName())
                .set("loginName", user.getLoginName())
        ).collect(Collectors.toList());
        return ResultPoJo.ok(Dict.create().set("checked", checked).set("userList", userList));
    }

    /**
     * 保存用户授权信息
     * @param roleId roleId
     * @return ResultPoJo
     */
    @PostMapping("/save/user/auth/{roleId}")
    @ApiOperation(value = "保存用户授权信息", notes = "保存用户授权信息", produces = "application/json")
    public ResultPoJo saveUser4RoleId(@PathVariable String roleId, String userIds) {
        //删除记录
        new UserRole().delete(new LambdaQueryWrapper<UserRole>().eq(UserRole::getRoleId, roleId));
        //保存记录
        Stream.of(userIds.split(",")).forEach(userId -> {
            new UserRole().setUserId(userId).setRoleId(roleId).insert();
        });
        return ResultPoJo.ok();
    }
}

