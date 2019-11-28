package com.spring.bacisic.admin.common.config.oauth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * oauth2.0授权 角色类
 *
 * @author zhangby
 * @date 2019-05-14 09:25
 */
@Data
@AllArgsConstructor
public class OauthRole implements GrantedAuthority {

    /**
     * 角色id
     */
    private String id;
    /**
     * 角色名称
     */
    private String name;

    public OauthRole(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
