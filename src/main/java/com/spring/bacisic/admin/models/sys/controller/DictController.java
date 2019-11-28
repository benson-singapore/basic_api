package com.spring.bacisic.admin.models.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import com.spring.bacisic.admin.common.entity.Pagination;
import com.spring.bacisic.admin.common.entity.ResultPoJo;
import com.spring.bacisic.admin.common.util.CommonUtil;
import com.spring.bacisic.admin.common.util.DictUtil;
import com.spring.bacisic.admin.common.validate.ValidateSave;
import com.spring.bacisic.admin.common.validate.ValidateUpdate;
import com.spring.bacisic.admin.models.sys.dto.DictQueryDto;
import com.spring.bacisic.admin.models.sys.dto.DictSaveDto;
import com.spring.bacisic.admin.models.sys.entity.Dict;
import com.spring.bacisic.admin.models.sys.service.IDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 'sys.statements_with_temp_tables' is not BASE TABLE 前端控制器
 * </p>
 *
 * @author zhangbiyu
 * @since 2019-11-23
 */
@RestController
@RequestMapping("/sys/dict")
@Api(tags = "字典管理")
public class DictController {

    @Autowired
    IDictService dictService;

    /**
     * 分页查询
     *
     * @return ResultPoJo
     */
    @GetMapping("")
    @ApiOperation(value = "分页查询", notes = "查询字典列表", produces = "application/json")
    public ResultPoJo<IPage<Dict>> getDictList4Page(Pagination pagination, DictQueryDto dictQueryDto) {
        IPage<Dict> page = dictService.page(pagination.page(), dictQueryDto.queryWrapper());
        return ResultPoJo.ok(page);
    }


    /**
     * 根据id获取字典
     *
     * @return ResultPoJo
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "查询字典记录", notes = "根据id获取字典信息", produces = "application/json")
    public ResultPoJo<Dict> getDictById(@PathVariable String id) {
        return ResultPoJo.ok(dictService.getById(id));
    }

    /**
     * 验证字典类型是否重复
     *
     * @return
     */
    @GetMapping("/verify/type/repeat/{type}")
    @ApiOperation(value = "验证字典类型是否重复", notes = "flag: true【重复】，false【不重复】", produces = "application/json")
    public ResultPoJo repeatType(@PathVariable String type, @RequestParam(value = "id", required = false) String id) {
        List<Dict> list = dictService.list(
                new LambdaQueryWrapper<Dict>().eq(Dict::getType, type).ne(Dict::getId, id)
        );
        return ResultPoJo.ok(cn.hutool.core.lang.Dict.create()
                .set("flag", !list.isEmpty())
        );
    }


    /**
     * 保存字典记录
     *
     * @return ResultPoJo
     */
    @PostMapping("")
    @ApiOperation(value = "保存字典记录", notes = "保存字典记录", produces = "application/json")

    public ResultPoJo saveDict(@Validated @RequestBody DictSaveDto dictSaveDto) {
        Dict dict = dictSaveDto.convert();
        dict.setParentId(Optional.ofNullable(dict.getParentId()).orElse("0"));
        dict.preInsert();
        dictService.save(dict);
        DictUtil.clear(dict.getType());
        return ResultPoJo.ok();
    }

    /**
     * 更新字典记录
     *
     * @param dictSaveDto
     * @return
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "更新字典记录", notes = "更新字典记录", produces = "application/json")
    public ResultPoJo updateDict(@PathVariable String id, @Validated @RequestBody DictSaveDto dictSaveDto) {
        Dict dict = dictSaveDto.convert().setId(id);
        dict.preUpdate();
        dictService.updateById(dict);
        DictUtil.clear(dict.getType());
        return ResultPoJo.ok();
    }

    /**
     * 删除字典
     *
     * @param id id
     * @return ResultPoJo
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除字典", notes = "删除字典", produces = "application/json")
    public ResultPoJo deleteDict(@PathVariable String id) {
        dictService.removeByIds(Lists.newArrayList(id.split(",")));
        DictUtil.clear(dictService.getById(id).getType());
        return ResultPoJo.ok();
    }

    /**
     * 查询字典最大排序值
     *
     * @param parentId parentId
     * @return ResultPoJo
     */
    @GetMapping("/max/sort")
    @ApiOperation(value = "查询字典最大排序值", notes = "查询字典最大排序值", produces = "application/json")
    public ResultPoJo getMaxSort(@RequestParam(value = "parentId",required = false) String parentId) {
        BigDecimal maxSort = dictService.list(new LambdaQueryWrapper<Dict>()
                .eq(Dict::getParentId, Optional.ofNullable(parentId).orElse("0"))).stream()
                .sorted((s1, s2) -> -s1.getSort().compareTo(s2.getSort()))
                .map(Dict::getSort)
                .findFirst().orElse(BigDecimal.ZERO);
        return ResultPoJo.ok(cn.hutool.core.lang.Dict.create()
                .set("maxSort", maxSort.add(new BigDecimal(10)))
        );
    }

    /**
     * 获取字典选择下拉框数据
     * @return
     */
    @GetMapping("/select/data/{type}")
    @ApiOperation(value = "获取字典选择下拉框数据", notes = "获取字典选择下拉框数据", produces = "application/json")
    public ResultPoJo<List<cn.hutool.core.lang.Dict>> getDictSelectData(@PathVariable String type) {
        List<cn.hutool.core.lang.Dict> rsList = CommonUtil.convers(DictUtil.getDictList4Type(type), dict -> cn.hutool.core.lang.Dict.create()
                .set("label", dict.getLabel())
                .set("value", dict.getValue())
        );
        return ResultPoJo.ok(rsList);
    }
}

