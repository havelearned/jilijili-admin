package top.jilijili.mall.shop.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import top.jilijili.common.entity.Result;
import top.jilijili.mall.shop.service.UserRatingsService;
import top.jilijili.module.pojo.entity.shop.UserRatings;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;

/**
 * 用户评分管理
 *
 * @author Amani
 * @date 2023年10月20日 23:54
 */
@RestController
@RequestMapping("/userBehavior")
@AllArgsConstructor
public class UserRatingsController {

    private UserRatingsService userRatingsService;


    /**
     * 用户评分列表
     *
     * @param userRatings
     * @return
     */
    @GetMapping("/list")
    public Mono<Result<Page<UserRatings>>> getUserRatings(UserRatings userRatings) {
        return Mono.fromCallable(() -> Result.ok(this.userRatingsService.lambdaQuery()
                        .eq(Objects.nonNull(userRatings.getItemId()), UserRatings::getItemId, userRatings.getItemId())
                        .eq(Objects.nonNull(userRatings.getUserId()), UserRatings::getUserId, userRatings.getUserId())
                        .eq(Objects.nonNull(userRatings.getRating()), UserRatings::getRating, userRatings.getRating())
                        .page(new Page<>(userRatings.getPage(), userRatings.getSize() + 100L))))
                .subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * 获取用评分信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Mono<Result<UserRatings>> getUserRatings(@PathVariable("id") Serializable id) {
        return Mono.just(Result.ok(this.userRatingsService.getById(id)));
    }

    /**
     * 添加用户评分
     *
     * @param userRatings
     * @return
     */
    @PostMapping
    public Mono<Boolean> add(@RequestBody UserRatings userRatings) {
        long unixTimestampMilliseconds = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        userRatings.setTimestamp(unixTimestampMilliseconds);
        return Mono.fromCallable(() -> this.userRatingsService.save(userRatings))
                .subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * 修改用户评分
     *
     * @param userRatings
     * @return
     */
    @PutMapping
    public Mono<Boolean> update(@RequestBody UserRatings userRatings) {
        long unixTimestampMilliseconds = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        userRatings.setTimestamp(unixTimestampMilliseconds);
        return Mono.fromCallable(() -> this.userRatingsService.save(userRatings))
                .subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * 删除一个或者多个用户评分
     *
     * @param idList
     * @return
     */
    @DeleteMapping
    public Mono<Boolean> delete(@RequestBody List<Long> idList) {
        return Mono.fromCallable(() -> this.userRatingsService.removeByIds(idList))
                .subscribeOn(Schedulers.boundedElastic());
    }


}
