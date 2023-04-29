package wang.jilijili.common.core.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wang.jilijili.common.core.pojo.bo.SysDictCovertBo;
import wang.jilijili.common.core.pojo.dto.SysDictDataDto;
import wang.jilijili.common.core.pojo.dto.SysDictTypeDto;
import wang.jilijili.common.core.pojo.entity.SysDictData;
import wang.jilijili.common.core.pojo.entity.SysDictType;
import wang.jilijili.common.core.pojo.vo.Result;
import wang.jilijili.common.core.service.SysDictDataService;
import wang.jilijili.common.core.service.SysDictTypeService;

import java.io.Serializable;
import java.util.List;

/**
 * 字典管理
 *
 * @author makejava
 * @since 2023-04-29 11:36:50
 */
@RestController
@RequestMapping("/sys/dict")
public class SysDictTypeController {
    /**
     * 服务对象
     */
    SysDictTypeService sysDictTypeService;
    SysDictDataService sysDictDataService;
    @Resource
    SysDictCovertBo sysDictCovertBo;

    @Autowired
    public SysDictTypeController(SysDictTypeService sysDictTypeService, SysDictDataService sysDictDataService) {
        this.sysDictTypeService = sysDictTypeService;
        this.sysDictDataService = sysDictDataService;
    }

    /**
     * 分页查询所有数据
     *
     * @param dictTypeDto 查询实体
     * @return 所有数据
     */
    @GetMapping("/list")
    public Result<IPage<SysDictType>> selectAll(SysDictTypeDto dictTypeDto) {
        IPage<SysDictType> page = new Page<>(dictTypeDto.getPage(), dictTypeDto.getSize());
        SysDictType sysDictType = sysDictCovertBo.toSysDictType(dictTypeDto);
        return Result.ok(this.sysDictTypeService.page(page, new QueryWrapper<>(sysDictType)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public Result<SysDictType> selectOne(@PathVariable Serializable id) {
        return Result.ok(this.sysDictTypeService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysDictType 实体对象
     * @return 新增结果
     */
    @PostMapping("/")
    public Result<Boolean> insert(@RequestBody SysDictType sysDictType) {
        return Result.ok(this.sysDictTypeService.save(sysDictType));
    }

    /**
     * 修改数据
     *
     * @param sysDictType 实体对象
     * @return 修改结果
     */
    @PutMapping("/")
    public Result<Boolean> update(@RequestBody SysDictType sysDictType) {
        return Result.ok(this.sysDictTypeService.updateById(sysDictType));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/")
    public Result<Boolean> delete(@RequestParam("idList") List<Long> idList) {
        return Result.ok(this.sysDictTypeService.removeByIds(idList));
    }


    /**
     * 分页查询所有数据
     *
     * @param sysDictDataDto 查询实体
     * @return 所有数据
     */
    @GetMapping("/item/list")
    public Result<IPage<SysDictData>> selectAllItem(SysDictDataDto sysDictDataDto) {
        IPage<SysDictData> page = new Page<>(sysDictDataDto.getPage(), sysDictDataDto.getSize());
        SysDictData sysDictData = this.sysDictCovertBo.toSysDictData(sysDictDataDto);
        return Result.ok(this.sysDictDataService.page(page, new QueryWrapper<>(sysDictData)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/item/{id}")
    public Result<SysDictData> selectOneItem(@PathVariable Serializable id) {
        return Result.ok(this.sysDictDataService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysDictData 实体对象
     * @return 新增结果
     */
    @PostMapping("/item/")
    public Result<Boolean> insertItem(@RequestBody SysDictData sysDictData) {
        return Result.ok(this.sysDictDataService.save(sysDictData));
    }

    /**
     * 修改数据
     *
     * @param sysDictData 实体对象
     * @return 修改结果
     */
    @PutMapping("/item/")
    public Result<Boolean> updateItem(@RequestBody SysDictData sysDictData) {
        return Result.ok(this.sysDictDataService.updateById(sysDictData));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/item/")
    public Result<Boolean> deleteItem(@RequestParam("idList") List<Long> idList) {
        return Result.ok(this.sysDictDataService.removeByIds(idList));
    }

}

