package top.jilijili.mall.currency.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.jilijili.common.control.SuperController;
import top.jilijili.common.entity.Result;
import top.jilijili.mall.currency.service.VirtualCurrencyService;
import top.jilijili.module.entity.VirtualCurrency;

import java.io.Serializable;
import java.util.List;

/**
 * 虚拟货币管理
 *
 * @author makejava
 * @since 2023-10-08 23:47:55
 */
@RestController
@RequestMapping("/virtualCurrency")
@AllArgsConstructor
public class VirtualCurrencyController extends SuperController {
    /**
     * 服务对象
     */
    private VirtualCurrencyService virtualCurrencyService;


    /**
     * 分页查询所有数据
     *
     * @param page                分页对象
     * @param shopVirtualCurrency 查询实体
     * @return 所有数据
     */
    @GetMapping("/list")
    public Result<?> selectAll(Page<VirtualCurrency> page, VirtualCurrency shopVirtualCurrency) {
        return Result.ok(this.virtualCurrencyService.page(page, new QueryWrapper<>(shopVirtualCurrency)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<?> selectOne(@PathVariable Serializable id) {
        return Result.ok(this.virtualCurrencyService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param shopVirtualCurrency 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<?> insert(@RequestBody VirtualCurrency shopVirtualCurrency) {
        return Result.ok(this.virtualCurrencyService.save(shopVirtualCurrency));
    }

    /**
     * 修改数据
     *
     * @param shopVirtualCurrency 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<?> update(@RequestBody VirtualCurrency shopVirtualCurrency) {
        return Result.ok(this.virtualCurrencyService.updateById(shopVirtualCurrency));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result<?> delete(@RequestBody List<Long> idList) {
        return Result.ok(this.virtualCurrencyService.removeByIds(idList));
    }
}

