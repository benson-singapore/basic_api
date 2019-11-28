package com.spring.bacisic.admin.common.entity;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * Dto 基础类
 *
 * @author zhangby
 * @date 23/11/19 6:28 pm
 */
public abstract class BaseDto<T extends Model<T>> {

    /**
     * 类型转换
     * @return T
     */
    public T convert() {
        return null;
    }

    /**
     * 查询条件
     * @return LambdaQueryWrapper
     */
    public LambdaQueryWrapper<T> queryWrapper() {
        return new LambdaQueryWrapper<T>();
    }
}
