package com.spring.bacisic.admin.common.entity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Optional;

/**
 * 分页参数
 *
 * @author zhangby
 * @date 2019-05-15 14:04
 */
@Data
@AllArgsConstructor
public class Pagination {

    /** 当前页数 */
    @Builder.Default
    @ApiModelProperty(value = "page number（当前页数）", example = "1")
    private Integer pageNum;
    /** 每页页数 */
    @Builder.Default
    @ApiModelProperty(value = "page size (每页页数)", example = "10")
    private Integer pageSize;

    /**
     * 获取分页对象
     * @return Page
     */
    public Page page() {
        return new Page(
                Optional.ofNullable(this.pageNum).orElse(1),
                Optional.ofNullable(this.pageSize).orElse(10)
        );
    }
}
