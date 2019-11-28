package com.spring.bacisic.admin.common.config.oauth.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.spring.bacisic.admin.common.config.oauth.entity.OauthRole;
import com.spring.bacisic.admin.common.config.oauth.entity.OauthUser;
import com.spring.bacisic.admin.common.constants.Constants;
import com.spring.bacisic.admin.common.util.CommonUtil;
import com.spring.bacisic.admin.models.sys.entity.Role;
import com.spring.bacisic.admin.models.sys.entity.User;
import com.spring.bacisic.admin.models.sys.entity.enums.RoleTypeEnum;
import com.spring.bacisic.admin.models.sys.mapper.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.OpenOption;
import java.util.List;
import java.util.Optional;

/**
 * 加载用户特定数据的核心接口（Core interface which loads user-specific data.）
 *
 * @author zhangby
 * @date 2019-05-14 09:49
 */
@Service
public class UserServiceDetail implements UserDetailsService {

    @Autowired
    RoleDao roleDao;

    /**
     * 根据用户名查询用户
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String authType = System.getProperty(Constants.AUTH_TYPE);
        OauthUser oauthUser = new OauthUser();
        //查询登录用户
        User user = new User().selectOne(new LambdaQueryWrapper<User>().eq(User::getLoginName, username));
        Optional.of(user).ifPresent(us ->{
            //查询角色
            List<Role> roleByUser = roleDao.getRoleByUser(user.getId());
            //数据类型转换
            List<OauthRole> oauthRoles = CommonUtil
                    .convers(roleByUser, role -> new OauthRole(
                            role.getId(),
                            Optional.ofNullable(role).map(Role::getEnname).orElse(null))
                    );
            //数据结果集封装
            oauthUser.setId(us.getId());
            oauthUser.setPassword(user.getPassword());
            oauthUser.setUsername(user.getLoginName());
            oauthUser.setOauthRoles(oauthRoles);
        });
        //判断登录入口
        return oauthUser;
    }
}
