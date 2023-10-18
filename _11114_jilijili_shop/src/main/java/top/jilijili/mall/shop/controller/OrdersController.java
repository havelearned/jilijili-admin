package top.jilijili.mall.shop.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import top.jilijili.common.entity.Result;
import top.jilijili.mall.shop.mapper.ConvertMapper;
import top.jilijili.module.pojo.entity.shop.Orders;
import top.jilijili.module.pojo.dto.shop.OrdersDto;
import top.jilijili.module.pojo.vo.shop.OrdersVo;
import top.jilijili.mall.shop.service.OrdersService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
     * 总订单量:完成,未完成,过期
     * 获取指定时间内的总订单量:完成,未完成,过期
     * 订单今日数量:完成 未完成 过期
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
        return Mono.just(Result.ok(ordersService.getOrderInfoById(orderId)));
    }

    /**
     * 通过用户id查询用户的订单
     *
     * @param ordersDto 查询对象
     * @return
     */
    @GetMapping("/user")
    public Mono<Result<IPage<OrdersVo>>> getOrdersByUserId(OrdersDto ordersDto) {

        return Mono.just(Result.ok(this.ordersService.getOrderListByUserId(ordersDto)));
    }

    /**
     * 查询所有订单数据
     *
     * @param ordersDto 查询对象
     * @return
     */
    @GetMapping("/list")
    public Mono<Result<IPage<OrdersVo>>> list(OrdersDto ordersDto) {
        return Mono.just(Result.ok(this.ordersService.getOrderList(ordersDto)));
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
    @PutMapping
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