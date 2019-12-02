package com.spring.bacisic.admin.models.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import com.spring.bacisic.admin.common.entity.Pagination;
import com.spring.bacisic.admin.common.entity.ResultPoJo;
import com.spring.bacisic.admin.common.exception.MyBaselogicException;
import com.spring.bacisic.admin.common.util.CommonUtil;
import com.spring.bacisic.admin.common.util.UserUtil;
import com.spring.bacisic.admin.common.validate.ValidateSave;
import com.spring.bacisic.admin.models.sys.dto.UserQueryDto;
import com.spring.bacisic.admin.models.sys.dto.UserSaveDto;
import com.spring.bacisic.admin.models.sys.entity.User;
import com.spring.bacisic.admin.models.sys.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * <p>
 * 'sys.statements_with_temp_tables' is not BASE TABLE 前端控制器
 * </p>
 *
 * @author zhangbiyu
 * @since 2019-11-22
 */
@RestController
@RequestMapping("/sys/user")
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    IUserService userService;

    @Value("${user.defaultPwd:123456}")
    private String defaultPwd;

    /**
     * 查询用户分页信息
     *
     * @param pagination
     * @return
     */
    @GetMapping("")
    @ApiOperation(value = "分页查询", notes = "分页查询", produces = "application/json")
    public ResultPoJo<IPage<User>> getUserList4Page(Pagination pagination, UserQueryDto userQueryDto) {
        IPage<User> page = userService.page(pagination.page(), userQueryDto.queryWrapper());
        //预处理
        CommonUtil.convers(page.getRecords(), userService.preInit());
        return ResultPoJo.ok(page);
    }

    /**
     * 根据id查询用户信息
     *
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "查询用户", notes = "根据id查询用户信息", produces = "application/json")
    public ResultPoJo<User> getUserById(@PathVariable String id) {
        return ResultPoJo.ok(userService.preInit().apply(UserUtil.getUser(id)));
    }

    /**
     * 获取当前登录用户
     * @return
     */
    @GetMapping("/get")
    @ApiOperation(value = "获取当前登录用户", notes = "获取当前登录用户", produces = "application/json")
    public ResultPoJo<User> getUser() {
        return ResultPoJo.ok(UserUtil.getUser());
    }

    /**
     * 保存用户信息
     *
     * @return
     */
    @PostMapping("")
    @ApiOperation(value = "保存用户", notes = "保存用户信息", produces = "application/json")
    public ResultPoJo saveUser(@Validated(ValidateSave.class) @RequestBody UserSaveDto userSaveDto) {
        userService.saveUser(userSaveDto);
        return ResultPoJo.ok();
    }

    /**
     * 更新用户信息
     *
     * @return
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "修改用户", notes = "根据id修改用户信息", produces = "application/json")
    public ResultPoJo updateUser(@PathVariable String id, @RequestBody UserSaveDto userSaveDto) {
        userService.updateUser(id, userSaveDto);
        UserUtil.clear(id);
        return ResultPoJo.ok();
    }

    /**
     * 删除用户
     *
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户", notes = "根据id删除用户，支持批量删除，多用户用逗号间隔", produces = "application/json")
    public ResultPoJo deleteUser(@PathVariable String id) {
        userService.removeByIds(Lists.newArrayList(id.split(",")));
        UserUtil.clear(id.split(","));
        return ResultPoJo.ok();
    }

    /**
     * 修改密码
     *
     * @param oldPwd
     * @param newPwd
     * @return
     */
    @PutMapping("/update/pwd")
    @ApiOperation(value = "修改密码", notes = "修改密码", produces = "application/json")
    public ResultPoJo updatePwd(@RequestParam("oldPwd") String oldPwd, @RequestParam("newPwd") String newPwd) {
        User user = UserUtil.getUser();
        Optional.ofNullable(user).orElseThrow(() -> new MyBaselogicException("107"));
        //比较密码是否正确
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(oldPwd, user.getPassword())) {
            //update pwd
            user.setPassword(passwordEncoder.encode(newPwd)).updateById();
        } else {
            throw new MyBaselogicException("103");
        }
        UserUtil.clear(UserUtil.getCurrentUserId());
        return ResultPoJo.ok();
    }

    /**
     * 重置密码
     *
     * @return
     */
    @PutMapping("/reset/pwd/{id}")
    @ApiOperation(value = "重置密码", notes = "重置密码", produces = "application/json")
    public ResultPoJo resetPwd(@PathVariable String id) {
        if (UserUtil.isAdmin()) {
            for (String userId : id.split(",")) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                userService.updateById(new User().setId(userId).setPassword(passwordEncoder.encode(defaultPwd)));
                UserUtil.clear(userId);
            }
        } else {
            throw new MyBaselogicException("403");
        }
        return ResultPoJo.ok();
    }
}

