package top.jilijili.shop.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import top.jilijili.common.entity.Result;
import top.jilijili.module.entity.Orders;
import top.jilijili.module.entity.dto.OrdersDto;
import top.jilijili.module.entity.vo.OrdersVo;
import top.jilijili.shop.mapper.ConvertMapper;
import top.jilijili.shop.service.OrdersService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 订单管理
 */
@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;
    private final ConvertMapper convertMapper;

    /*---------------------------订单------------------------------*/

    /**
     * * # 总订单量:完成,未完成,过期
     * * # 获取指定时间内的总订单量:完成,未完成,过期
     * * # 订单今日数量:完成 未完成 过期
     *
     * @param ordersDto
     * @return
     */
    @GetMapping("/orderToDayInfo")
    public Mono<Result<Map<String, Object>>> orderToDayInfo(OrdersDto ordersDto) {
        return Mono.just(Result.ok(this.ordersService.queryOrdersTodayData(ordersDto)));
    }

    /**
     * 通过id查询订单信息
     *
     * @param orderId 订单id
     * @return
     */
    @GetMapping("/{orderId}")
    public Mono<Result<OrdersVo>> getOrderById(@PathVariable Serializable orderId) {
        return Mono.just(Result.ok(this.convertMapper.toOrdersVo(ordersService.getById(orderId))));
    }

    /**
     * 通过用户id查询用户的订单
     *
     * @param ordersDto 查询对象
     * @return
     */
    @GetMapping("/user/{userId}")
    public Mono<Result<IPage<OrdersVo>>> getOrdersByUserId(OrdersDto ordersDto) {
        return Mono.just(Result.ok(this.ordersService.lambdaQuery()
                .eq(!Objects.isNull(ordersDto.getUserId()), Orders::getUserId, ordersDto.getUserId())
                .page(new Page<>(ordersDto.getPage(), ordersDto.getSize()))
                .convert(this.convertMapper::toOrdersVo)));
    }

    /**
     * 创建订单
     *
     * @param ordersDto 添加对象
     * @return
     */
    @PostMapping
    public Mono<Result<Orders>> createOrder(@RequestBody OrdersDto ordersDto) {
        Orders orders = this.convertMapper.toOrders(ordersDto);
        boolean success = ordersService.save(orders);
        return Mono.just(success ? Result.ok(orders) : Result.fail("操作失败"));
    }

    /**
     * 修改订单
     *
     * @param ordersDto 厂修改对象
     * @return
     */
    @PutMapping("/{orderId}")
    public Mono<Result<Orders>> updateOrder(@RequestBody OrdersDto ordersDto) {
        Orders orders = this.convertMapper.toOrders(ordersDto);
        boolean success = ordersService.updateById(orders);
        return Mono.just(success ? Result.ok(orders) : Result.fail("操作失败"));
    }

    /**
     * 删除订单
     *
     * @param idList 一个或者多个
     * @return
     */
    @DeleteMapping
    public Mono<Result<Object>> deleteOrder(List<Serializable> idList) {
        return Mono.fromCallable(() -> {
            boolean success = ordersService.removeBatchByIds(idList);
            return success ? Result.ok(null) : Result.fail("操作失败");
        }).subscribeOn(Schedulers.boundedElastic());
    }
}