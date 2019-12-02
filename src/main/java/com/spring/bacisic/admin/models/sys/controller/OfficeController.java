package com.spring.bacisic.admin.models.sys.controller;


import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spring.bacisic.admin.common.entity.Pagination;
import com.spring.bacisic.admin.common.entity.ResultPoJo;
import com.spring.bacisic.admin.common.util.CommonUtil;
import com.spring.bacisic.admin.models.sys.entity.Office;
import com.spring.bacisic.admin.models.sys.service.IOfficeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 查询部门分页信息
     * @param pagination
     * @return
     */
    @GetMapping("")
    @ApiOperation(value = "分页查询", notes = "分页查询", produces = "application/json")
    public ResultPoJo<IPage<Office>> getOfficeList4Page(Pagination pagination) {
        IPage page = officeService.page(pagination.page());
        return ResultPoJo.ok(page);
    }

    /**
     * 部门查询
     * @param id id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "部门详情", notes = "根据id查询部门信息", produces = "application/json")
    public ResultPoJo<Office> getOfficeById(@PathVariable String id) {
        Office office = officeService.getById(id);
        return ResultPoJo.ok(office);
    }

    /**
     * 保存部门
     * @return
     */
    @PostMapping("")
    @ApiOperation(value = "保存部门", notes = "保存部门", produces = "application/json")
    public ResultPoJo saveOffice() {
        return ResultPoJo.ok();
    }

    /**
     * 更新部门
     * @return
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "更新部门", notes = "更新部门", produces = "application/json")
    public ResultPoJo updateOffice(@PathVariable String id) {
        return ResultPoJo.ok();
    }

    /**
     * 删除部门
     * @param id id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除部门", notes = "删除部门", produces = "application/json")
    public ResultPoJo deleteOffice(@PathVariable String id) {
        officeService.removeById(id);
        return ResultPoJo.ok();
    }

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

