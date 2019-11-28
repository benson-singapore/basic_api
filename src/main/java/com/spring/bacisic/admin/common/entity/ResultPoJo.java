package com.spring.bacisic.admin.common.entity;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.spring.bacisic.admin.common.constants.Constants;
import com.spring.bacisic.admin.common.util.CommonUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 返回数据
 *
 * @author zhangby
 * @date 2019-05-13 12:11
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ResultPoJo", description = "返回数据")
public class ResultPoJo<T> {

    @ApiModelProperty(value = "返回码：000【正确】，其他错误", example = "000")
    private String code;
    @ApiModelProperty(value = "返回信息", example = "成功！")
    private String msg;
    @ApiModelProperty(value = "返回结果", example = "{}")
    private T result;

    private ResultPoJo() {
        this.code = Constants.NORMAL;
    }

    /**
     * 链式构造
     *
     * @return ResultPoJo
     */
    public static ResultPoJo ok() {
        ResultPoJo poJo = new ResultPoJo();
        poJo.code = Constants.NORMAL;
        poJo.msg = Constants.MSG_000;
        return poJo;
    }

    /**
     * 链式构造
     *
     * @return ResultPoJo
     */
    public static ResultPoJo error() {
        ResultPoJo poJo = new ResultPoJo();
        poJo.code = Constants.ERROR;
        poJo.msg = Constants.MSG_999;
        return poJo;
    }

    /**
     * 创建成功返回结果
     */
    public static ResultPoJo ok(String s, Object rs) {
        return ResultPoJo.ok().setMsg(s).setResult(rs);
    }

    public static ResultPoJo ok(Object s) {
        return ResultPoJo.ok().setResult(s);
    }

    /**
     * 创建失败返回结果
     */
    public static ResultPoJo error(String num, Object... rs) {
        return CommonUtil.loadException2ResultPoJo(num, rs);
    }

    public String toJson() {
        SerializeConfig config = new SerializeConfig();
        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        return JSON.toJSONString(this, config, null, "yyyy-MM-dd HH:mm:ss", 1);
    }

    /**
     * 数据转换
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T toBean(Class<T> clazz) {
        T convert = null;
        try {
            convert = Convert.convert(clazz, getResult());
        } catch (Exception e) {
            convert = JSON.parseObject(JSON.toJSONString(getResult()), clazz);
        }
        return (T) convert;
    }
}
