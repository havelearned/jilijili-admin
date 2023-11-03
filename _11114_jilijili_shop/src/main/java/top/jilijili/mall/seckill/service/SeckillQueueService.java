package top.jilijili.mall.seckill.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 秒杀队列
 *
 * @author Amani
 * @date 2023年11月02日 23:55
 */
@Service
@AllArgsConstructor
@Slf4j
public class SeckillQueueService {

    private final ExecutorService secKillThreadPool;

    private final SeckillService seckillService;


    public boolean kill(Integer userId, Integer productId) {
        // 处理合并秒杀并发队列，最多等待1秒
        Future<Boolean> submit = secKillThreadPool.submit(() -> seckillService.handleSeckillLock(productId, userId));

        try {
            if (submit.isDone()) {
                Boolean b = submit.get(1, TimeUnit.SECONDS);
                log.info("秒杀结果: {}", b);
                return b;
            } else {
                log.info("秒杀失败");
                return false;
            }
        } catch (TimeoutException e) {
            log.error("秒杀超时: {}", e.getMessage());
            // 在这里可以添加适当的超时处理逻辑
            return false;
        } catch (Exception e) {
            log.error("发生异常: ", e);
            // 在这里可以添加其他异常处理逻辑
            return false;
        }
    }


}
