package com.spring.bacisic.admin.models.sys.mapper;

import com.spring.bacisic.admin.models.sys.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author zhangbiyu
 * @since 2019-11-27
 */
@Repository
public interface MenuDao extends BaseMapper<Menu> {

    List<Menu> getMenu4User(@Param("userId") String currentUserId);

}
