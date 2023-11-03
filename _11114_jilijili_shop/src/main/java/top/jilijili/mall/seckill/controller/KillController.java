package top.jilijili.mall.seckill.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import top.jilijili.common.entity.Result;
import top.jilijili.common.utils.KeyConstants;
import top.jilijili.mall.seckill.service.SeckillQueueService;

/**
 * 秒杀服务
 *
 * @author Amani
 * @date 2023年11月03日 0:03
 */
@RestController
@RequestMapping("/kill")
@Slf4j
public class KillController {
    @Autowired
    private SeckillQueueService queueService;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;


    @PostMapping("/killGoods")
    public Result<String> killGoods(@RequestParam(value = "userId") Integer userId,
                                    @RequestParam(value = "productId") Integer productId) throws InterruptedException {
        // 模拟用户认证耗时操作
        Thread.sleep(200);

        // 得到商品库存
        Integer stockCount = (Integer) redisTemplate.opsForValue().get(KeyConstants.PRODUCT_HEA_KEY + productId);
        if (stockCount != null && stockCount > 0) {
            // 库存足够,进入秒杀队列
            if (queueService.kill(userId, productId)) {
                // 秒杀成功
                return Result.ok("秒杀成功");
            } else {
                // 秒杀失败
                return Result.fail("秒杀失败");
            }
        } else {
            return Result.fail("库存不足");
        }
    }


}
