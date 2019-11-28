package com.spring.bacisic.admin.common.config.oauth.entity;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * oauth2.0授权 用户类
 *
 * @author zhangby
 * @date 2019-05-13 16:22
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "OauthUser")
public class OauthUser implements UserDetails {

    /**
     * 用户id
     */
    @ApiModelProperty(hidden = true)
    private String id;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "username（登录名）", example = "admin")
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty(value = "password（密码）", example = "123")
    private String password;
    /**
     * 用户角色
     */
    @ApiModelProperty(hidden = true)
    private List<OauthRole> oauthRoles = Lists.newArrayList();

    @Override
    @ApiModelProperty(hidden = true)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.oauthRoles;
    }

    @Override
    @ApiModelProperty(hidden = true)
    public String getPassword() {
        return this.password;
    }

    @Override
    @ApiModelProperty(hidden = true)
    public String getUsername() {
        return this.username;
    }

    @Override
    @ApiModelProperty(hidden = true)
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @ApiModelProperty(hidden = true)
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @ApiModelProperty(hidden = true)
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @ApiModelProperty(hidden = true)
    public boolean isEnabled() {
        return true;
    }
}
