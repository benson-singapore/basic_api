package com.spring.bacisic.admin.common.util;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.spring.bacisic.admin.common.constants.Constants;
import com.spring.bacisic.admin.common.service.IRedisService;
import com.spring.bacisic.admin.models.sys.entity.User;
import com.spring.bacisic.admin.models.sys.entity.enums.RoleTypeEnum;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用户工具类
 *
 * @author zhangby
 * @date 27/9/19 5:32 pm
 */
public class UserUtil {

    /**
     * redis service
     */
    private static IRedisService redisService = SpringContextUtil.getBean(IRedisService.class);

    /**
     * 获取当前访问用户id
     *
     * @return
     */
    public static String getCurrentUserId() {
        String userId = System.getProperty(Constants.CURRENT_USER_ID);
        return Optional.ofNullable(userId).orElseGet(()->
            CommonUtil.resolve(() -> {
                String tokenValue = ((OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getTokenValue();
                Claims claims = CommonUtil.parseJWT(tokenValue);
                Object uid = null;
                if (claims != null) {
                    uid = claims.get("user_id");
                }
                return Optional.ofNullable(uid).map(String::valueOf).filter(StrUtil::isNotBlank).orElse(null);
            }).orElse(null)
        );
    }

    /**
     * 获取当前用户授权角色
     *
     * @return
     */
    public static List<String> getAuthorities() {
        Collection<? extends GrantedAuthority> authorities = Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getAuthorities)
                .orElse(null);
        return Optional.ofNullable(authorities).map(li -> li.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())).orElse(Lists.newArrayList());
    }

    /**
     * 系统管理员
     * @return
     */
    public static Boolean isAdmin() {
        List<String> authorities = getAuthorities();
        return authorities.contains(RoleTypeEnum.admin.toString());
    }

    /**
     * 获取当前登录用户
     * @return
     */
    public static User getUser() {
        return new User().selectById(getCurrentUserId());
    }
}
