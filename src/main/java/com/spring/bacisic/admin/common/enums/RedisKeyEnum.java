package com.spring.bacisic.admin.common.enums;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Redis key 枚举
 *
 * @author zhangby
 * @date 14/10/19 2:02 pm
 */
public enum RedisKeyEnum {
    /**
     * redis for access_token key
     */
    AUTH_TOKEN("auth:token:{}"),
    /**
     * app user for redis key
     */
    REDIS_KEY_USER_ID("user:id:{}"),
    /**
     * redis for dict key
     */
    REDIS_KEY_DICT_TYPE("dict:type:{}");

    private String key;

    RedisKeyEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

    /**
     * 枚举转list
     *
     * @return
     */
    public static List<Dict> toList() {
        return Stream.of(values())
                .collect(
                        ArrayList::new,
                        (li, item) -> li.add(Dict.create()
                                .set("label", StrUtil.format(item.getKey(), ""))
                                .set("value", StrUtil.format(item.getKey(), ""))
                        ),
                        List::addAll
                );
    }
}
