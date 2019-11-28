package com.spring.bacisic.admin.models.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.spring.bacisic.admin.common.exception.MyBaselogicException;
import com.spring.bacisic.admin.common.util.DictUtil;
import com.spring.bacisic.admin.models.sys.dto.UserSaveDto;
import com.spring.bacisic.admin.models.sys.entity.Office;
import com.spring.bacisic.admin.models.sys.entity.User;
import com.spring.bacisic.admin.models.sys.entity.UserRole;
import com.spring.bacisic.admin.models.sys.entity.enums.DictTypeEnum;
import com.spring.bacisic.admin.models.sys.mapper.UserDao;
import com.spring.bacisic.admin.models.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 'sys.statements_with_temp_tables' is not BASE TABLE 服务实现类
 * </p>
 *
 * @author zhangbiyu
 * @since 2019-11-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {

    /**
     * 预加载数据
     * @return
     */
    @Override
    public Function<User, User> preInit() {
        return user -> {
            Optional.ofNullable(new Office().selectById(user.getOfficeId()))
                    .ifPresent(of -> user.set("officeName", of.getName()));
            Optional.ofNullable(DictUtil.getDictLabel(DictTypeEnum.roleType, user.getUserType()))
                    .ifPresent(label -> user.set("userTypeLabel", label));
            List<UserRole> userRoles = new UserRole()
                    .selectList(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, user.getId()));
            user.set("roleIds",userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList()));
            return user;
        };
    }

    /**
     * 保存用户
     *
     * @param userSaveDto userSaveDto
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveUser(UserSaveDto userSaveDto) {
        //验证用户名是否存在
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>().eq(User::getLoginName, userSaveDto.getLoginName());
        List<User> list = list(queryWrapper);
        if (!list.isEmpty()) {
            throw new MyBaselogicException("111");
        }
        User user = userSaveDto.convert();
        user.preInsert();
        save(user);
        // 插入角色
        for (String roleId : userSaveDto.getRoleId().split(",")) {
            new UserRole().setRoleId(roleId).setUserId(user.getId()).insert();
        }
    }

    /**
     * 更新用户
     *
     * @param id id
     * @param userSaveDto u
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUser(String id, UserSaveDto userSaveDto) {
        //验证用户名是否存在
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>()
                .eq(User::getLoginName, userSaveDto.getLoginName())
                .ne(User::getId, id);
        List<User> list = list(queryWrapper);
        if (!list.isEmpty()) {
            throw new MyBaselogicException("111");
        }
        User user = userSaveDto.convert();
        user.setId(id);
        user.preUpdate();
        updateById(user);
        //刷新角色信息
        new UserRole().delete(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, user.getId()));
        // 插入角色
        for (String roleId : userSaveDto.getRoleId().split(",")) {
            new UserRole().setRoleId(roleId).setUserId(user.getId()).insert();
        }
    }
}
