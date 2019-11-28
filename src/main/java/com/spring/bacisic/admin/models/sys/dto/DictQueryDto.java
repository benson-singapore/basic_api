package com.spring.bacisic.admin.models.sys.dto;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.spring.bacisic.admin.common.entity.BaseDto;
import com.spring.bacisic.admin.common.enums.BaseEnum;
import com.spring.bacisic.admin.common.util.CommonUtil;
import com.spring.bacisic.admin.models.sys.entity.Dict;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Optional;

/**
 * 字典查询dto
 *
 * @author zhangby
 * @date 23/11/19 6:26 pm
 */
@Data
@Accessors(chain = true)
public class DictQueryDto extends BaseDto<Dict> {
    /** 关键字 */
    @ApiModelProperty("标签名")
    private String label;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("父类id")
    private String parentId;

    private String keyword;

    @Override
    public LambdaQueryWrapper<Dict> queryWrapper() {
        LambdaQueryWrapper<Dict> queryWrapper = super.queryWrapper();
        // 模糊查询
        CommonUtil.emptyStr(label).ifPresent(key -> queryWrapper.like(Dict::getLabel,key));
        CommonUtil.emptyStr(type).ifPresent(key -> queryWrapper.like(Dict::getType,key));
        CommonUtil.emptyStr(keyword).ifPresent(key -> {
            queryWrapper.and(aqw -> {
                aqw.or(nqw -> nqw.like(Dict::getLabel, key))
                        .or(nqw -> nqw.like(Dict::getValue, key))
                        .or(nqw -> nqw.like(Dict::getDescription, key));
            });
        });
        //查询父类id
        String parentId = Optional.ofNullable(this.parentId).filter(StrUtil::isNotBlank).orElse("0");
        queryWrapper.eq(Dict::getParentId, parentId);
        //排序
        queryWrapper.orderByAsc(Dict::getSort).orderByDesc(Dict::getCreateDate);
        return queryWrapper;
    }
}
