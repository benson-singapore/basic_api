package com.spring.bacisic.admin.models.sys.service.impl;

import com.spring.bacisic.admin.common.util.DictUtil;
import com.spring.bacisic.admin.models.sys.entity.Dict;
import com.spring.bacisic.admin.models.sys.entity.Role;
import com.spring.bacisic.admin.models.sys.entity.enums.DictTypeEnum;
import com.spring.bacisic.admin.models.sys.mapper.RoleDao;
import com.spring.bacisic.admin.models.sys.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * <p>
 * 'sys.statements_with_temp_tables' is not BASE TABLE 服务实现类
 * </p>
 *
 * @author zhangbiyu
 * @since 2019-11-22
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements IRoleService {

    /**
     * 初始化
     * @return
     */
    @Override
    public Function<Role, Role> preInit() {
        return role -> {
            //查询角色类型
            role.set("roleTypeLabel", DictUtil.getDictLabel(DictTypeEnum.roleType, role.getRoleType()));
            return role;
        };
    }
}
