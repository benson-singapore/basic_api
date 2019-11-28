package com.spring.bacisic.admin.common.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.spring.bacisic.admin.common.enums.RedisKeyEnum;
import com.spring.bacisic.admin.common.service.IRedisService;
import com.spring.bacisic.admin.models.sys.entity.Dict;
import com.spring.bacisic.admin.models.sys.entity.enums.DictTypeEnum;

import java.util.List;
import java.util.stream.Collectors;

/**
 * dict util
 *
 * @author zhangby
 * @date 2019-05-21 16:03
 */
public class DictUtil {

    /** redis service */
    private static IRedisService redisService = SpringContextUtil.getBean(IRedisService.class);

    /**
     * get all DictList
     * @param type dict_type
     * @return List<Dict>
     */
    public static List<Dict> getDictList4Type(String type) {
        List<Dict> dictList = Lists.newArrayList();
        CommonUtil.emptyStr(type).ifPresent(dict_type->{
            // get value by redis
            String dictKey = StrUtil.format(RedisKeyEnum.REDIS_KEY_DICT_TYPE.getKey(), type);
            List<Dict> arrayBean = redisService.getArrayBean(dictKey, Dict.class);
            if (ObjectUtil.isNull(arrayBean)) {
                //query for DB
                List<Dict> dicts = new Dict().selectList(new LambdaQueryWrapper<Dict>().eq(Dict::getType, type).orderByAsc(Dict::getSort));
                redisService.set(dictKey, dicts);
                arrayBean = dicts;
            }
            dictList.addAll(arrayBean);
        });
        return dictList.stream().filter(dict -> !"0".equals(dict.getParentId())).collect(Collectors.toList());
    }

    /**
     * 根据枚举获取字典
     * @param dictTypeEnum
     * @return
     */
    public static List<Dict> getDictList4Type(DictTypeEnum dictTypeEnum) {
        return getDictList4Type(dictTypeEnum.getValue());
    }

    /**
     * 根据label获取字典
     * @param dictTypeEnum
     * @param label
     * @return
     */
    public static Dict getDict4Label(DictTypeEnum dictTypeEnum, String label) {
        //get label
        return getDictList4Type(dictTypeEnum).stream()
                .filter(dict -> dict.getLabel().equals(label))
                .findFirst()
                .orElse(null);
    }

    /**
     * 根据label获取字典
     * @param dictTypeEnum
     * @param value
     * @return
     */
    public static Dict getDict4Value(DictTypeEnum dictTypeEnum, String value) {
        //get label
        return getDictList4Type(dictTypeEnum).stream()
                .filter(dict -> dict.getValue().equals(value))
                .findFirst()
                .orElse(null);
    }

    /**
     * get dict label by dict_type and dict_value
     * @param dictTypeEnum dictTypeEnum
     * @param value dict_value
     * @return String
     */
    public static String getDictLabel(DictTypeEnum dictTypeEnum, String value) {
        if (ObjectUtil.isNull(value)) {
            return null;
        }
        //get label
        return getDictList4Type(dictTypeEnum).stream()
                .filter(dict -> value.equals(dict.getValue()))
                .findFirst()
                .map(dict -> dict.getLabel())
                .orElse(null);
    }

    /**
     * get dict value by dict_type and dict_label
     * @param type dict type
     * @param label dict label
     * @return
     */
    public static String getDictValue(String type, String label) {
        //get label
        return getDictList4Type(type).stream()
                .filter(dict -> dict.getLabel().equals(label))
                .findFirst()
                .map(dict -> dict.getValue())
                .orElse(null);
    }

    /**
     * clear dict
     */
    public static void clear(String type) {
        String dict_key = StrUtil.format(RedisKeyEnum.REDIS_KEY_DICT_TYPE.getKey(), type);
        redisService.remove(dict_key);
    }

    /**
     * clear dict all
     */
    public static void clearAll() {
        clear("*");
    }

}