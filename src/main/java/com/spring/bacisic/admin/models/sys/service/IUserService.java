package com.spring.bacisic.admin.models.sys.service;

import com.spring.bacisic.admin.models.sys.dto.UserSaveDto;
import com.spring.bacisic.admin.models.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.function.Function;

/**
 * <p>
 * 'sys.statements_with_temp_tables' is not BASE TABLE 服务类
 * </p>
 *
 * @author zhangbiyu
 * @since 2019-11-22
 */
public interface IUserService extends IService<User> {

    /**
     * 预加载数据
     * @return user
     */
    Function<User, User> preInit();

    /**
     * 保存用户
     * @param userSaveDto u
     */
    void saveUser(UserSaveDto userSaveDto);

    /**
     * 更新用户
     * @param id
     * @param userSaveDto u
     */
    void updateUser(String id, UserSaveDto userSaveDto);
}
