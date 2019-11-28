package com.spring.bacisic.admin.models.sys.service;

import com.spring.bacisic.admin.models.sys.entity.Role;
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
public interface IRoleService extends IService<Role> {

    /**
     * 初始化
     * @return function
     */
    Function<Role, Role> preInit();

}
