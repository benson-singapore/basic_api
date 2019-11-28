package com.spring.bacisic.admin.models.sys.dto;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.spring.bacisic.admin.common.entity.BaseDto;
import com.spring.bacisic.admin.common.util.CommonUtil;
import com.spring.bacisic.admin.models.sys.entity.User;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户查询dto
 *
 * @author zhangby
 * @date 25/11/19 4:16 pm
 */
@Data
@Accessors(chain = true)
public class UserQueryDto extends BaseDto<User> {
    private String name;
    private String loginName;
    private String mobile;
    private String email;
    private String userType;

    @Override
    public LambdaQueryWrapper<User> queryWrapper() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        //设置查询条件
        CommonUtil.emptyStr(name).ifPresent(str -> queryWrapper.like(User::getName, str));
        CommonUtil.emptyStr(loginName).ifPresent(str -> queryWrapper.like(User::getLoginName, str));
        CommonUtil.emptyStr(mobile).ifPresent(str -> queryWrapper.like(User::getMobile, str));
        CommonUtil.emptyStr(email).ifPresent(str -> queryWrapper.like(User::getEmail, str));
        CommonUtil.emptyStr(userType).ifPresent(str -> queryWrapper.eq(User::getUserType, str));
        //排序
        queryWrapper.orderByDesc(User::getCreateDate);
        return queryWrapper;
    }
}
