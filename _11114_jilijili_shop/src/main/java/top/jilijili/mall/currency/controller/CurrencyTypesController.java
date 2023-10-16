package top.jilijili.mall.currency.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.jilijili.common.control.SuperController;
import top.jilijili.common.entity.Result;
import top.jilijili.mall.currency.service.CurrencyTypesService;
import top.jilijili.module.entity.CurrencyTypes;

import java.io.Serializable;
import java.util.List;

/**
 * 货币类型管理
 *
 * @author makejava
 * @since 2023-10-08 23:47:55
 */
@RestController
@RequestMapping("/currencyTypes")
@AllArgsConstructor
public class CurrencyTypesController extends SuperController {
    /**
     * 服务对象
     */
    private CurrencyTypesService currencyTypesService;

    /**
     * 分页查询所有数据
     *
     * @param page              分页对象
     * @param shopCurrencyTypes 查询实体
     * @return 所有数据
     */
    @GetMapping("/type/list")
    public Result<Page<CurrencyTypes>> selectAll(Page<CurrencyTypes> page, CurrencyTypes shopCurrencyTypes) {
        return Result.ok(this.currencyTypesService.page(page, new QueryWrapper<>(shopCurrencyTypes)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/type/{id}")
    public Result<CurrencyTypes> selectOne(@PathVariable Serializable id) {
        return Result.ok(this.currencyTypesService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param currencyTypes 实体对象
     * @return 新增结果
     */
    @PostMapping("/type")
    public Result<CurrencyTypes> insert(@RequestBody CurrencyTypes currencyTypes) {
        return this.currencyTypesService.save(currencyTypes) ? Result.ok(currencyTypes, "操作成功") : Result.fail(currencyTypes, "操作失败");
    }

    /**
     * 修改数据
     *
     * @param currencyTypes 实体对象
     * @return 修改结果
     */
    @PutMapping("/type")
    public Result<CurrencyTypes> update(@RequestBody CurrencyTypes currencyTypes) {
        return this.currencyTypesService.updateById(currencyTypes) ? Result.ok(currencyTypes, "操作成功") : Result.fail(currencyTypes, "操作失败");
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/type")
    public Result<Boolean> delete(@RequestBody List<Long> idList) {
        return Result.ok(this.currencyTypesService.removeByIds(idList), "操作成功");
    }
}

