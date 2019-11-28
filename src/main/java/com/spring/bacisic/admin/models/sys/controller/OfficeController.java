package com.spring.bacisic.admin.models.sys.controller;


import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.spring.bacisic.admin.common.entity.ResultPoJo;
import com.spring.bacisic.admin.common.util.CommonUtil;
import com.spring.bacisic.admin.models.sys.entity.Office;
import com.spring.bacisic.admin.models.sys.service.IOfficeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 职位表 前端控制器
 * </p>
 *
 * @author zhangbiyu
 * @since 2019-11-26
 */
@RestController
@RequestMapping("/sys/office")
@Api(tags = "部门管理")
public class OfficeController {

    @Autowired
    IOfficeService officeService;

    /**
     * 查询部门选择下拉框
     * @return
     */
    @GetMapping("/select/data")
    @ApiOperation(value = "查询部门选择下拉框", notes = "查询部门选择下拉框", produces = "application/json")
    public ResultPoJo<Dict> getOfficeSelectData() {
        LambdaQueryWrapper<Office> queryWrapper = new LambdaQueryWrapper<Office>()
                .orderByAsc(Office::getSort)
                .orderByDesc(Office::getCreateDate);
        List<Dict> rsList = CommonUtil.convers(officeService.list(ObjectUtil.clone(queryWrapper).eq(Office::getParentId, "0")), role -> Dict.create()
                .set("label", role.getName())
                .set("value", role.getId())
        );
        rsList.forEach(dict -> {
            List<Dict> children = CommonUtil.convers(officeService.list(queryWrapper.eq(Office::getParentId, dict.getStr("value"))), role -> Dict.create()
                    .set("label", role.getName())
                    .set("value", role.getId())
            );
            dict.set("children", children);
        });
        return ResultPoJo.ok(rsList);
    }
}

