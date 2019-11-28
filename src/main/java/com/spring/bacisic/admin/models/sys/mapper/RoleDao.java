package com.spring.bacisic.admin.models.sys.mapper;

import com.spring.bacisic.admin.models.sys.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 'sys.statements_with_temp_tables' is not BASE TABLE Mapper 接口
 * </p>
 *
 * @author zhangbiyu
 * @since 2019-11-22
 */
@Repository
public interface RoleDao extends BaseMapper<Role> {
    /**
     * query user info by userId
     *
     * @param userId
     * @return List
     */
    List<Role> getRoleByUser(@Param("userId") String userId);

}
