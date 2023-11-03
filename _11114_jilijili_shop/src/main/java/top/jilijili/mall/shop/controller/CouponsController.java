package top.jilijili.mall.shop.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import top.jilijili.common.control.SuperController;
import top.jilijili.common.entity.Result;
import top.jilijili.mall.shop.service.CouponsService;
import top.jilijili.mall.shop.service.PromocodesService;
import top.jilijili.mall.shop.service.UserCouponsService;
import top.jilijili.module.pojo.dto.shop.CouponsDto;
import top.jilijili.module.pojo.dto.shop.PromocodesDto;
import top.jilijili.module.pojo.dto.shop.UserCouponsDto;
import top.jilijili.module.pojo.entity.shop.Coupons;
import top.jilijili.module.pojo.entity.shop.Promocodes;
import top.jilijili.module.pojo.vo.shop.CouponsVo;
import top.jilijili.module.pojo.vo.shop.PromocodesVo;
import top.jilijili.module.pojo.vo.shop.UserCouponsVo;
import top.jilijili.module.pojo.vo.shop.UserWithCouponsVo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 兑换码优惠卷管理
 *
 * @author Amani
 * @date 2023年10月06日 23:19
 */
@RestController
@RequestMapping("/coupons")
@AllArgsConstructor
public class CouponsController extends SuperController {


    private CouponsService couponsService;
    private UserCouponsService userCouponsService;
    private PromocodesService promocodesService;

    /*====================================兑换码服务===========================================*/


    /**
     * 兑换码分页查询所有数据
     *
     * @param promocodesDto 查询实体
     * @return 所有数据
     */
    @GetMapping("/promocodes/list")
    public Mono<Result<IPage<PromocodesVo>>> selectAllPromocodes(PromocodesDto promocodesDto) {
        return Mono.just(Result.ok(this.promocodesService.selectAllPromocodes(promocodesDto)));
    }

    /**
     * 兑换码 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/promocodes/{id}")
    public Mono<Result<PromocodesVo>> selectOnePromocodes(@PathVariable Serializable id) {
        return Mono.just(this.promocodesService.selectOnePromocodes(id));
    }

    /**
     * 兑换码 新增数据
     *
     * @param promocodesDto 实体对象
     * @return 新增结果
     */
    @PostMapping("/promocodes")
    public Mono<Result<PromocodesVo>> insertPromocodes(@RequestBody PromocodesDto promocodesDto) {
        return Mono.just(this.promocodesService.insertPromocodes(promocodesDto));
    }


    /**
     * 兑换码 批量生成
     *
     * @param promocodesDto 实体对象
     * @return 新增结果
     */
    @PostMapping("/promocodes/batch")
    public Mono<Result<List<Promocodes>>> batchPromocodes(@RequestBody PromocodesDto promocodesDto) {
        return Mono.just(this.promocodesService.batchPromocodes(promocodesDto));
    }

    /**
     * 兑换码 修改数据
     *
     * @param promocodesDto 实体对象
     * @return 修改结果
     */
    @PutMapping("/promocodes")
    public Mono<Result<PromocodesVo>> updatePromocodes(@RequestBody PromocodesDto promocodesDto) {
        return Mono.just(this.promocodesService.updatePromocodes(promocodesDto));
    }

    /**
     * 兑换码 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/promocodes")
    public Mono<Result<?>> deletePromocodes(@RequestBody List<Long> idList) {
        return Mono.fromCallable(() -> {
            boolean removed = this.promocodesService.removeByIds(idList);
            return removed ? Result.ok("操作成功") : Result.fail("操作失败");
        }).subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * 兑换码导出
     *
     * @param response      导出数据
     * @param promocodesDto 筛选条件
     */
    @GetMapping("/pormocodes/export")
    public void promocodesExport(HttpServletResponse response, PromocodesDto promocodesDto) {
        Mono<Result<IPage<PromocodesVo>>> resultMono = this.selectAllPromocodes(promocodesDto);
        Result<IPage<PromocodesVo>> result = resultMono.block();
        List<PromocodesVo> records = result.getData().getRecords();
        super.exportData(response, "兑换码数据", "第一页", PromocodesVo.class, records);
    }

    /*====================================兑换码服务===========================================*/


    /*====================================优惠卷===========================================*/

    /**
     * 用户的优惠卷分页查询所有数据
     *
     * @param coupons 查询实体
     * @return 所有数据
     */
    @GetMapping("/coupons/user/list")
    public Mono<Result<IPage<UserWithCouponsVo>>> selectAllUserCoupons(CouponsDto coupons) {
        return Mono.just(this.couponsService.selectAllUserCoupons(coupons));
    }


    /**
     * 优惠卷页查询所有数据
     *
     * @param coupons 查询实体
     * @return 所有数据
     */
    @GetMapping("/coupons/list")
    public Mono<Result<Page<Coupons>>> selectAllCoupons(CouponsDto coupons) {
        return Mono.just(this.couponsService.selectAllCoupons(coupons));
    }

    /**
     * 优惠卷 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/coupons/{id}")
    public Mono<Result<CouponsVo>> selectOneCoupons(@PathVariable Serializable id) {
        return Mono.just(this.couponsService.selectOneCoupons(id));
    }

    /**
     * 优惠卷 新增数据
     *
     * @param couponsDto 实体对象
     * @return 新增结果
     */
    @PostMapping("/coupons")
    public Mono<Result<CouponsVo>> insertCoupons(@RequestBody CouponsDto couponsDto) {
        return Mono.just(this.couponsService.insertCoupons(couponsDto));
    }

    /**
     * 优惠卷 修改数据
     *
     * @param couponsDto 实体对象
     * @return 修改结果
     */
    @PutMapping("/coupons")
    public Mono<Result<CouponsVo>> updateCoupons(@RequestBody CouponsDto couponsDto) {
        return Mono.just(this.couponsService.updateCoupons(couponsDto));
    }

    /**
     * 优惠卷 不提供删除
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/coupons")
    public Result<String> deleteCoupons(@RequestBody List<Long> idList) {
        return this.couponsService.removeByIds(idList) ? Result.ok("操作成功") : Result.fail("操作失败");
    }

    /*====================================优惠卷===========================================*/


    /*====================================兑换码&优惠卷使用情况===========================================*/


    /**
     * 用户优惠卷分页查询
     * @param userId 用户id
     * @param isUsed 是否可用 0可用 1不可用
     * @return
     */
    @GetMapping("/userCoupons/list/{userId}/{isUsed}")
    public Mono<Result<IPage<Map<String, Object>>>> selectAllUC(
            @PathVariable
            Serializable userId,
            @PathVariable(required = false) Integer isUsed) {
        return Mono.just(this.userCouponsService.selectAllUC(userId,isUsed));
    }

    /**
     * 优惠卷 新增数据
     *
     * @param userCouponsDto 实体对象
     * @return 新增结果
     */
    @PostMapping("/userCoupons")
    public Mono<Result<UserCouponsVo>> insertUc(@RequestBody UserCouponsDto userCouponsDto) {

        return Mono.just(this.userCouponsService.insertUc(userCouponsDto));
    }

    /**
     * 优惠卷 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/userCoupons/{id}")
    public Mono<Result<UserCouponsVo>> selectOneUC(@PathVariable Serializable id) {
        return Mono.just(this.userCouponsService.selectOneUC(id));
    }

    /**
     * 优惠卷 修改数据
     *
     * @param userCouponsDto 实体对象
     * @return 修改结果
     */
    @PutMapping("/userCoupons")
    public Mono<Result<UserCouponsVo>> updateUC(@RequestBody UserCouponsDto userCouponsDto) {
        return Mono.just(this.userCouponsService.updateUC(userCouponsDto));
    }

    /**
     * 优惠卷 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/userCoupons")
    public Result<String> deleteUC(@RequestBody List<Long> idList) {
        return Result.ok(this.userCouponsService.removeByIds(idList) ? "操作成功" : "操作失败");
    }
    /*====================================兑换码优惠卷使用情况===========================================*/


}
