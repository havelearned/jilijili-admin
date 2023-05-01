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
import java.util.Comparator;
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
     * TODO 通过字典类型查询当下所有数据
     * */

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
     * @param sysDictTypeDto 实体对象
     * @return 新增结果
     */
    @PostMapping("/")
    public Result<Boolean> insert(@RequestBody SysDictTypeDto sysDictTypeDto) {
        return Result.ok(this.sysDictTypeService.create(sysDictTypeDto));
    }

    /**
     * 修改数据
     *
     * @param sysDictTypeDto 实体对象
     * @return 修改结果
     */
    @PutMapping("/")
    public Result<Boolean> update(@RequestBody SysDictTypeDto sysDictTypeDto) {
        return Result.ok(this.sysDictTypeService.update(sysDictTypeDto));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/{idList}")
    public Result<Boolean> delete(@PathVariable List<String> idList) {
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
        page  = this.sysDictDataService.page(page, new QueryWrapper<>(sysDictData));
        page.getRecords().sort(Comparator.comparing(SysDictData::getDictSort));
        return Result.ok(page);
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
    @DeleteMapping("/item/{idList}")
    public Result<Boolean> deleteItem(@PathVariable List<String> idList) {
        return Result.ok(this.sysDictDataService.removeByIds(idList));
    }


    /**
     * 修改致字典状态
     *
     * @param sysDictData 实体类
     * @return true
     */
    @PutMapping("/item/status")
    public Result<Boolean> updateByStatus(@RequestBody SysDictData sysDictData) {
        if (sysDictData.getStatus() == 0) {
            sysDictData.setStatus(1);
        } else {
            sysDictData.setStatus(0);
        }
        return Result.ok(this.sysDictDataService.updateById(sysDictData));
    }


}

