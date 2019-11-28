package com.spring.bacisic.admin.models.sys.service.impl;

import com.spring.bacisic.admin.models.sys.entity.Menu;
import com.spring.bacisic.admin.models.sys.mapper.MenuDao;
import com.spring.bacisic.admin.models.sys.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author zhangbiyu
 * @since 2019-11-27
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements IMenuService {

    @Autowired
    MenuDao menuDao;

    /**
     * 根据用户查询菜单
     * @param currentUserId currentUserId
     * @return List<Menu>
     */
    @Override
    public List<Menu> getMenu4User(String currentUserId) {
        return menuDao.getMenu4User(currentUserId);
    }

}
