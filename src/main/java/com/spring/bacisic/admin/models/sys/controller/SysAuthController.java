package com.spring.bacisic.admin.models.sys.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.spring.bacisic.admin.common.entity.ResultPoJo;
import com.spring.bacisic.admin.common.enums.RedisKeyEnum;
import com.spring.bacisic.admin.common.exception.MyBaselogicException;
import com.spring.bacisic.admin.common.service.IRedisService;
import com.spring.bacisic.admin.common.util.CommonUtil;
import com.spring.bacisic.admin.common.util.UserUtil;
import com.spring.bacisic.admin.models.sys.entity.User;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 系统用户授权管理
 *
 * @author zhangby
 * @date 22/11/19 4:37 pm
 */
@RestController
@RequestMapping("/sys/oauth")
@Api(tags = "授权管理")
public class SysAuthController {

    /**
     * port
     */
    @Value("${server.port:8080}")
    private String host;


    @Autowired
    IRedisService redisService;


    /**
     * Verify that the login is invalid
     *
     * @return []
     */
    @GetMapping("/login/verify")
    @ApiOperation(value = "登录验证", notes = "登录验证：flag【true 成功】，【false 失败】", produces = "application/json", response = ResultPoJo.class)
    public ResultPoJo verifyLogin(@ApiIgnore HttpServletRequest request) {
        List<String> rsList = Lists.newArrayList();
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        //解析token
        Claims claims = CommonUtil.parseJWT(token);
        Optional.ofNullable(claims).ifPresent(cl -> {
            String tokenKey = StrUtil.format(RedisKeyEnum.AUTH_TOKEN.getKey(), cl.getId());
            Object user = redisService.get(tokenKey);
            Optional.ofNullable(user).ifPresent(val -> {
                //更新登录超时时间
                redisService.set(tokenKey, user, 60L * 60L);
                rsList.addAll(Convert.toList(String.class, claims.get("authorities")));
            });
        });
        return ResultPoJo.ok(rsList);
    }

    /**
     * login
     *
     * @param username 用户名
     * @param password 密码
     * @return User
     */
    @GetMapping("/login")
    @ApiOperation(value = "登录授权", notes = "登录授权接口，获取token", produces = "application/json")
    public ResultPoJo<User> login(
            @ApiParam(example = "admin", value = "用户名") @RequestParam("username") String username,
            @ApiParam(example = "123456", value = "密码") @RequestParam("password") String password,
            @ApiIgnore HttpServletRequest request
    ) {
        ResultPoJo resultPoJo = ResultPoJo.ok();
        try {
            //query user
            User user = new User().selectOne(new LambdaQueryWrapper<User>().eq(User::getLoginName, username));
            Optional.ofNullable(user).orElseThrow(() -> new MyBaselogicException("100"));
            //Call login interface
            String rs = HttpRequest.post("http://localhost:" + host + "/oauth/token")
                    .header("Authorization", "Basic dWFhLXNlcnZpY2U6MTIzNDU2")
                    .form(Dict.create()
                            .set("username", username)
                            .set("password", password)
                            .set("grant_type", "password")
                            .set("auth_type", "")
                    ).execute().body();
            Map map = JSON.parseObject(rs, Map.class);
            Object access_token = map.get("access_token");
            //Verify that the access_token is empty
            if (ObjectUtil.isNull(access_token)) {
                throw new MyBaselogicException("103");
            }
            user.set("access_token", "Bearer " + access_token);
            //add redis
            String token_key = StrUtil.format(RedisKeyEnum.AUTH_TOKEN.getKey(), map.get("jti"));
            redisService.set(token_key, user, 60L * 60L);
            /** 登录成功刷新用户ip */
            resultPoJo.setResult(user);
        } catch (Exception e) {
            if (e instanceof MyBaselogicException) {
                throw e;
            } else {
                throw new MyBaselogicException("102");
            }
        }
        return resultPoJo;
    }

    /**
     * logout
     *
     * @return
     */
    @GetMapping("/logout")
    @ApiOperation(value = "退出登录", notes = "退出登录接口", produces = "application/json", response = ResultPoJo.class)
    public ResultPoJo logout(@ApiIgnore HttpServletRequest request) {
        /** 解析token */
        Claims claims = CommonUtil.parseJWT(request.getHeader(HttpHeaders.AUTHORIZATION));
        Optional.ofNullable(claims).ifPresent(cl -> {
            String token_key = "auth:token:" + cl.getId();
            redisService.remove(token_key);
        });
        return ResultPoJo.ok();
    }
}
