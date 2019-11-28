package com.spring.bacisic.admin.common.interceptor;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.google.common.collect.Lists;
import com.spring.bacisic.admin.common.constants.Constants;
import com.spring.bacisic.admin.common.entity.ResultPoJo;
import com.spring.bacisic.admin.common.enums.RedisKeyEnum;
import com.spring.bacisic.admin.common.exception.MyBaselogicException;
import com.spring.bacisic.admin.common.service.IRedisService;
import com.spring.bacisic.admin.common.util.CommonUtil;
import com.spring.bacisic.admin.common.util.SpringContextUtil;
import com.spring.bacisic.admin.common.util.UserUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * AccessToken filter
 *
 * @author zhangby
 * @date 2019-05-20 20:32
 */
public class AccessTokenFilter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * redis service
     */
    IRedisService redisService = SpringContextUtil.getBean(IRedisService.class);


    /**
     * do filter
     *
     * @param servletRequest  servletRequest
     * @param servletResponse servletResponse
     * @param filterChain     filterChain
     * @throws IOException      IOException
     * @throws ServletException ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            // filter url
            if (urlMatcher(request.getRequestURI(), Constants.FILTER_EXCLUDE_PATH)) {
                String token = request.getHeader(HttpHeaders.AUTHORIZATION);
                /** 解析token */
                Claims claims = CommonUtil.parseJWT(token);
                if (ObjectUtil.isNotNull(claims)) {
                    //设置当前登录用户
                    System.setProperty(Constants.CURRENT_USER_ID, claims.get("user_id").toString());
                    try {
                        //获取redis 查询token是否有效 [jti]
                        String tokenKey = StrUtil.format(RedisKeyEnum.AUTH_TOKEN.getKey(), claims.getId());
                        Object user = redisService.get(tokenKey);
                        if (ObjectUtil.isNotNull(user)) {
                            //重置失效时长 (默认1小时)
                            redisService.set(tokenKey, user, 60L * 60L);
                        } else {
                            logger.info("998：登录超时，无效认证");
                            //登录超时
                            returnJson(response, ResultPoJo.error("998").toJson());
                            return;
                        }
                    } catch (Exception e) {
                        logger.info("401：非授权访问，无效的token");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //系统异常
            returnJson(response, ResultPoJo.error("997").toJson());
        }
        filterChain.doFilter(servletRequest, servletResponse);
        //过滤器结束之后销毁
        System.clearProperty(Constants.CURRENT_USER_ID);
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {

    }

    /**
     * 认证是否需要，验证session url
     *
     * @param real_url
     * @return
     */
    private boolean urlMatcher(String real_url, String pathFilter) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        /** 验证添加项url */
        if (StrUtil.isNotBlank(pathFilter)) {
            for (String path : pathFilter.split(",")) {
                if (antPathMatcher.match(path.trim(), real_url.trim())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 返回url
     *
     * @param response
     * @param json
     */
    private void returnJson(HttpServletResponse response, String json) {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (IOException e) {
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
