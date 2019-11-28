package com.spring.bacisic.admin.models.sys.dto;

import com.spring.bacisic.admin.common.entity.BaseDto;
import com.spring.bacisic.admin.common.validate.ValidateSave;
import com.spring.bacisic.admin.common.validate.ValidateUpdate;
import com.spring.bacisic.admin.models.sys.entity.Dict;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * 字典保存Dto
 *
 * @author zhangby
 * @date 23/11/19 8:10 pm
 */
@Data
@Accessors(chain = true)
public class DictSaveDto extends BaseDto<Dict> {

    @ApiModelProperty(value = "数据值")
    private String value;

    @NotBlank(message = "标签名不能为空")
    @ApiModelProperty(value = "标签名")
    private String label;

    @NotBlank(message = "类型不能为空")
    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "排序（升序）")
    private BigDecimal sort;

    @ApiModelProperty(value = "父级编号")
    private String parentId;

    @Override
    public Dict convert() {
        Dict dict = new Dict();
        this.sort = Optional.ofNullable(sort).orElse(new BigDecimal(10));
        BeanUtils.copyProperties(this, dict);
        return dict;
    }
}
