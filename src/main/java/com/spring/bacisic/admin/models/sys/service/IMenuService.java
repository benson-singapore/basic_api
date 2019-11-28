package com.spring.bacisic.admin.models.sys.service;

import com.spring.bacisic.admin.models.sys.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author zhangbiyu
 * @since 2019-11-27
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> getMenu4User(String currentUserId);
}
