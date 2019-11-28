package com.spring.bacisic.admin.common.interceptor;

import com.spring.bacisic.admin.common.constants.Constants;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * 添加用户授权统一过滤【对登录类型支持】
 *
 * @author zhangby
 * @date 2019-04-09 16:41
 */
@Component
public class LoginAuthenticationFilter extends GenericFilterBean implements ApplicationContextAware {

    private static final String OAUTH_TOKEN_URL = "/oauth/token";

    private RequestMatcher requestMatcher;

    public LoginAuthenticationFilter(){
        this.requestMatcher = new OrRequestMatcher(
                new AntPathRequestMatcher(OAUTH_TOKEN_URL, "GET"),
                new AntPathRequestMatcher(OAUTH_TOKEN_URL, "POST")
        );
    }

    /**
     * 过滤拦截
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if(requestMatcher.matches(request)){
            try{
                //设置集成登录信息
                String auth_type = request.getParameter("auth_type");
                System.setProperty(Constants.AUTH_TYPE, Optional.ofNullable(auth_type).orElse(""));
                //预处理
                filterChain.doFilter(request,response);
                //后置处理
            }finally {
                System.clearProperty(Constants.AUTH_TYPE);
            }
        }else{
            filterChain.doFilter(request,response);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
