package top.jilijili.mall.shop.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.jilijili.common.entity.Result;
import top.jilijili.mall.shop.service.CouponsService;
import top.jilijili.mall.shop.service.PromocodesService;
import top.jilijili.mall.shop.service.UserCouponsService;
import top.jilijili.module.pojo.entity.shop.Promocodes;
import top.jilijili.module.pojo.dto.shop.CouponsDto;
import top.jilijili.module.pojo.dto.shop.PromocodesDto;
import top.jilijili.module.pojo.dto.shop.UserCouponsDto;
import top.jilijili.module.pojo.vo.shop.CouponsVo;
import top.jilijili.module.pojo.vo.shop.PromocodesVo;
import top.jilijili.module.pojo.vo.shop.UserCouponsVo;
import top.jilijili.module.pojo.vo.shop.UserWithCouponsVo;

import java.io.Serializable;
import java.util.List;

/**
 * 兑换码优惠卷管理
 *
 * @author Amani
 * @date 2023年10月06日 23:19
 */
@RestController
@RequestMapping("/coupons")
@AllArgsConstructor
public class CouponsController {

    // TODO 兑换码curd 优惠卷curd

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
    public Result<IPage<PromocodesVo>> selectAllPromocodes(PromocodesDto promocodesDto) {
        return Result.ok(this.promocodesService.selectAllPromocodes(promocodesDto));
    }

    /**
     * 兑换码 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/promocodes/{id}")
    public Result<PromocodesVo> selectOnePromocodes(@PathVariable Serializable id) {
        return this.promocodesService.selectOnePromocodes(id);
    }

    /**
     * 兑换码 新增数据
     *
     * @param promocodesDto 实体对象
     * @return 新增结果
     */
    @PostMapping("/promocodes")
    public Result<PromocodesVo> insertPromocodes(@RequestBody PromocodesDto promocodesDto) {
        return this.promocodesService.insertPromocodes(promocodesDto);
    }


    /**
     * 兑换码 批量生成
     *
     * @param promocodesDto 实体对象
     * @return 新增结果
     */
    @PostMapping("/promocodes/batch")
    public Result<List<Promocodes>> batchPromocodes(@RequestBody PromocodesDto promocodesDto) {
        return this.promocodesService.batchPromocodes(promocodesDto);
    }

    /**
     * 兑换码 修改数据
     *
     * @param promocodesDto 实体对象
     * @return 修改结果
     */
    @PutMapping("/promocodes")
    public Result<PromocodesVo> updatePromocodes(@RequestBody PromocodesDto promocodesDto) {
        return this.promocodesService.updatePromocodes(promocodesDto);
    }

    /**
     * 兑换码 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/promocodes")
    public Result<String> deletePromocodes(@RequestBody List<Long> idList) {
        return this.promocodesService.removeByIds(idList) ? Result.ok("操作成功") : Result.fail("操作失败");
    }

    /*====================================兑换码服务===========================================*/


    /*====================================优惠卷===========================================*/

    /**
     * 优惠卷分页查询所有数据
     *
     * @param coupons 查询实体
     * @return 所有数据
     */
    @GetMapping("/coupons/list")
    public Result<IPage<UserWithCouponsVo>> selectAllCoupons(CouponsDto coupons) {
        return this.couponsService.selectAllCoupons(coupons);
    }

    /**
     * 优惠卷 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/coupons/{id}")
    public Result<CouponsVo> selectOneCoupons(@PathVariable Serializable id) {
        return this.couponsService.selectOneCoupons(id);
    }

    /**
     * 优惠卷 新增数据
     *
     * @param couponsDto 实体对象
     * @return 新增结果
     */
    @PostMapping("/coupons")
    public Result<CouponsVo> insertCoupons(@RequestBody CouponsDto  couponsDto) {
        return this.couponsService.insertCoupons(couponsDto);
    }

    /**
     * 优惠卷 修改数据
     *
     * @param couponsDto 实体对象
     * @return 修改结果
     */
    @PutMapping("/coupons")
    public Result<CouponsVo> updateCoupons(@RequestBody CouponsDto couponsDto) {
        return this.couponsService.updateCoupons(couponsDto);
    }

    /**
     * 优惠卷 删除数据
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
     * 使用情况分页查询所有数据
     *
     * @param userCouponsDto 查询实体
     * @return 所有数据
     */
    @GetMapping("/userCoupons/list")
    public Result<IPage<UserCouponsVo>> selectAllUC(UserCouponsDto userCouponsDto) {
        return this.userCouponsService.selectAllUC(userCouponsDto);
    }

    /**
     * 使用情况 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/userCoupons/{id}")
    public Result<UserCouponsVo> selectOneUC(@PathVariable Serializable id) {
        return this.userCouponsService.selectOneUC(id);
    }

    /**
     * 使用情况 新增数据
     *
     * @param userCouponsDto 实体对象
     * @return 新增结果
     */
    @PostMapping("/userCoupons")
    public Result<UserCouponsVo> insertUc(@RequestBody UserCouponsDto userCouponsDto) {

        return this.userCouponsService.insertUc(userCouponsDto);
    }

    /**
     * 使用情况 修改数据
     *
     * @param userCouponsDto 实体对象
     * @return 修改结果
     */
    @PutMapping("/userCoupons")
    public Result<UserCouponsVo> updateUC(@RequestBody UserCouponsDto userCouponsDto) {
        return this.userCouponsService.updateUC(userCouponsDto);
    }

    /**
     * 使用情况 删除数据
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
