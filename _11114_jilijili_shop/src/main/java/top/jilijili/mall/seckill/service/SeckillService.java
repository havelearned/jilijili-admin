package top.jilijili.mall.seckill.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.jilijili.common.heandler.JiliException;
import top.jilijili.common.utils.KeyConstants;
import top.jilijili.mall.shop.service.OrderItemsService;
import top.jilijili.mall.shop.service.OrdersService;
import top.jilijili.mall.shop.service.ProductsService;
import top.jilijili.module.pojo.entity.shop.OrderItems;
import top.jilijili.module.pojo.entity.shop.Orders;
import top.jilijili.module.pojo.entity.shop.Products;

import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Amani
 * @date 2023年11月03日 0:08
 */
@Service
@AllArgsConstructor
@Slf4j
public class SeckillService {
    RedisTemplate<String, Object> redisTemplate;

    private final OrderItemsService orderItemService;
    private final OrdersService ordersService;
    private final ProductsService productsService;


    // 处理秒杀请求的方法
    @Transactional(rollbackFor = JiliException.class, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Boolean handleSeckill(Integer productId, Integer userId) {
        Integer stock = (Integer) redisTemplate.opsForValue().get(KeyConstants.PRODUCT_HEA_KEY + productId);

        if (stock == null || stock < 1) {
            return false;
        }

        Products products = this.productsService.getById(productId);
        if (products.getStockQuantity() < 0) {
            return false;
        } else {
            synchronized (this) {
                // 库存扣减
                products.setStockQuantity(products.getStockQuantity() - 1);
                Integer quantity = products.getStockQuantity();
                UpdateWrapper<Products> queryWrapper = new UpdateWrapper<Products>()
                        .eq("product_id", productId)
                        .ge("stock_quantity", 1)
                        .set("stock_quantity", quantity);
                boolean b = this.productsService.update(queryWrapper);
                // 设置缓存库存
                redisTemplate.opsForValue().decrement(KeyConstants.PRODUCT_HEA_KEY + productId);
            }

            Orders orders = new Orders();
            orders.setUserId(Long.valueOf(userId));
            orders.setOrderStatus(2);
            orders.setOrderDate(new Date());
            boolean save = this.ordersService.save(orders);

            OrderItems orderItems = new OrderItems();
            orderItems.setOrderId(orders.getOrderId());
            orderItems.setProductId(Long.valueOf(productId));
            orderItems.setQuantity(1);
            boolean saved = this.orderItemService.save(orderItems);

            return save && saved;
        }
    }

    @Transactional(rollbackFor = JiliException.class, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Boolean handleSeckillLock(Integer productId, Integer userId) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            Integer stock = (Integer) redisTemplate.opsForValue().get(KeyConstants.PRODUCT_HEA_KEY + productId);

            if (stock == null || stock < 1) {
                return false;
            }

            Products products = this.productsService.getById(productId);
            if (products.getStockQuantity() < 1) {
                return false;
            }

            // 库存扣减
            products.setStockQuantity(products.getStockQuantity() - 1);
            Integer quantity = products.getStockQuantity();
            UpdateWrapper<Products> queryWrapper = new UpdateWrapper<Products>()
                    .eq("product_id", productId)
                    .ge("stock_quantity", 1)
                    .set("stock_quantity", quantity);
            boolean b = this.productsService.update(queryWrapper);
            // 设置缓存库存
            redisTemplate.opsForValue().decrement(KeyConstants.PRODUCT_HEA_KEY + productId);

            // 发送MQ,生成订单
            Orders orders = new Orders();
            orders.setUserId(Long.valueOf(userId));
            orders.setOrderStatus(2);
            orders.setOrderDate(new Date());
            boolean save = this.ordersService.save(orders);

            OrderItems orderItems = new OrderItems();
            orderItems.setOrderId(orders.getOrderId());
            orderItems.setProductId(Long.valueOf(productId));
            orderItems.setQuantity(1);
            boolean save1 = this.orderItemService.save(orderItems);

            if (save && save1) {
                return true;
            } else {
                throw new JiliException("订单生成失败");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        } finally {
            lock.unlock();
        }
    }

}
