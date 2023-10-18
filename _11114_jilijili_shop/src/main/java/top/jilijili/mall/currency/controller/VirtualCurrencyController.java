package top.jilijili.mall.currency.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.jilijili.common.control.SuperController;
import top.jilijili.common.entity.Result;
import top.jilijili.mall.currency.service.VirtualCurrencyService;
import top.jilijili.module.pojo.dto.currency.VirtualCurrencyDto;
import top.jilijili.module.pojo.entity.currency.VirtualCurrency;
import top.jilijili.module.pojo.vo.currency.VirtualCurrencyVo;

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
@Slf4j
public class VirtualCurrencyController extends SuperController {
    /**
     * 服务对象
     */
    private VirtualCurrencyService virtualCurrencyService;


    /**
     * 分页查询所有数据
     *
     * @param dto 查询实体
     * @return 所有数据
     */
    @GetMapping("/list")
    public Result<Page<VirtualCurrencyVo>> selectAll(VirtualCurrencyDto dto) {
        return Result.ok(this.virtualCurrencyService.selectAll(dto));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<VirtualCurrencyVo> selectOne(@PathVariable Serializable id) {
        return Result.ok(this.virtualCurrencyService.selectOne(id));
    }


    /**
     * 修改数据
     *
     * @param virtualCurrency 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<?> update(@RequestBody VirtualCurrency virtualCurrency) {

        return this.virtualCurrencyService.updateById(virtualCurrency)
                ? Result.ok(virtualCurrency, "操作成功") : Result.fail("操作失败");
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result<Boolean> delete(@RequestBody List<Long> idList) {
        return this.virtualCurrencyService.removeByIds(idList) ? Result.ok(null, "操作成功") : Result.fail("操作失败");
    }
}

