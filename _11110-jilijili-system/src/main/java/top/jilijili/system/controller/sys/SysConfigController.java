package top.jilijili.system.controller.sys;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.jilijili.module.entity.SysConfig;
import top.jilijili.common.entity.Result;
import top.jilijili.system.controller.SuperController;
import top.jilijili.system.service.SysConfigService;

import java.io.Serializable;
import java.util.List;

/**
 * 系统配置表(SysConfig)表控制层
 *
 * @author makejava
 * @since 2023-08-01 00:03:12
 */
@RestController
@RequestMapping("/sysConfig")
@Slf4j
@AllArgsConstructor
public class SysConfigController extends SuperController {
    /**
     * 服务对象
     */
    private SysConfigService sysConfigService;

    /**
     * 分页查询所有数据
     *
     * @param page      分页对象
     * @param sysConfig 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result<?> selectAll(Page<SysConfig> page, SysConfig sysConfig) {
        return Result.ok(this.sysConfigService.page(page, new QueryWrapper<>(sysConfig)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<?> selectOne(@PathVariable Serializable id) {
        return Result.ok(this.sysConfigService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysConfig 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<?> insert(@RequestBody SysConfig sysConfig) {
        return Result.ok(this.sysConfigService.save(sysConfig));
    }

    /**
     * 修改数据
     *
     * @param sysConfig 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<?> update(@RequestBody SysConfig sysConfig) {
        return Result.ok(this.sysConfigService.updateById(sysConfig));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result<?> delete(@RequestParam("idList") List<Long> idList) {
        return Result.ok(this.sysConfigService.removeByIds(idList));
    }
}

