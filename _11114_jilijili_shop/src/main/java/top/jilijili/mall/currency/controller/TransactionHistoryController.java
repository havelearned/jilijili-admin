package top.jilijili.mall.currency.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.jilijili.common.entity.ApiController;
import top.jilijili.common.entity.R;
import top.jilijili.mall.currency.service.TransactionHistoryService;
import top.jilijili.module.entity.TransactionHistory;

import java.io.Serializable;
import java.util.List;

/**
 * 交易历史
 *
 * @author makejava
 * @since 2023-10-08 23:47:55
 */
@RestController
@RequestMapping("/transactionHistory")
@AllArgsConstructor
public class TransactionHistoryController extends ApiController {
    /**
     * 服务对象
     */
    private TransactionHistoryService TransactionHistoryService;

    /**
     * 分页查询所有数据
     *
     * @param page                   分页对象
     * @param shopTransactionHistory 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<TransactionHistory> page, TransactionHistory shopTransactionHistory) {
        return success(this.TransactionHistoryService.page(page, new QueryWrapper<>(shopTransactionHistory)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.TransactionHistoryService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param shopTransactionHistory 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody TransactionHistory shopTransactionHistory) {
        return success(this.TransactionHistoryService.save(shopTransactionHistory));
    }

    /**
     * 修改数据
     *
     * @param shopTransactionHistory 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody TransactionHistory shopTransactionHistory) {
        return success(this.TransactionHistoryService.updateById(shopTransactionHistory));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.TransactionHistoryService.removeByIds(idList));
    }
}

