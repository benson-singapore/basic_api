package com.spring.bacisic.admin.common.config.oauth.jwt;

import com.spring.bacisic.admin.common.config.oauth.entity.OauthUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * 自定义token
 *
 * @author zhangby
 * @date 2019-04-01 11:39
 */
public class JwtAccessToken extends JwtAccessTokenConverter {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
        // 设置额外用户信息
        OauthUser baseUser = ((OauthUser) authentication.getPrincipal());
        // 将用户信息添加到token额外信息中
        defaultOAuth2AccessToken.getAdditionalInformation().put("user_id", baseUser.getId());

        return super.enhance(defaultOAuth2AccessToken, authentication);
    }
}
