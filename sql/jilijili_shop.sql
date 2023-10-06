-- 商品分类表（categories）
DROP TABLE IF EXISTS shop_categories;
CREATE TABLE shop_categories
(
    category_id   BIGINT PRIMARY KEY COMMENT '分类ID',
    category_name VARCHAR(32) COMMENT '分类名称',
    created_time  DATETIME COMMENT '创建时间',
    updated_time  DATETIME COMMENT '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='商品分类表';

-- 商品表（products）
DROP TABLE IF EXISTS shop_products;
CREATE TABLE shop_products
(
    product_id     BIGINT PRIMARY KEY COMMENT '商品ID',
    category_id    BIGINT COMMENT '分类ID',
    product_name   VARCHAR(255) COMMENT '商品名称',
    description    TEXT COMMENT '商品描述',
    price          DECIMAL(10, 2) COMMENT '价格',
    stock_quantity INT COMMENT '库存数量',
    image_url      VARCHAR(255) COMMENT '图片链接',
    created_time   DATETIME COMMENT '创建时间',
    updated_time   DATETIME COMMENT '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='商品表';

-- 购物车表（carts）
DROP TABLE IF EXISTS shop_carts;
CREATE TABLE shop_carts
(
    cart_id BIGINT PRIMARY KEY COMMENT '购物车ID',
    user_id BIGINT COMMENT '用户ID'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='购物车表';

-- 购物车明细表（cart_items）
DROP TABLE IF EXISTS shop_cart_items;
CREATE TABLE shop_cart_items
(
    item_id      BIGINT PRIMARY KEY COMMENT '明细ID',
    cart_id      BIGINT COMMENT '购物车ID',
    product_id   BIGINT COMMENT '商品ID',
    quantity     INT COMMENT '数量',
    subtotal     DECIMAL(10, 2) COMMENT '小计金额',
    created_time DATETIME COMMENT '创建时间',
    updated_time DATETIME COMMENT '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='购物车明细表';

-- 订单表（orders）
DROP TABLE IF EXISTS shop_orders;
CREATE TABLE shop_orders
(
    order_id     BIGINT PRIMARY KEY COMMENT '订单ID',
    user_id      BIGINT COMMENT '用户ID',
    order_date   DATETIME COMMENT '下单日期',
    order_status VARCHAR(50) COMMENT '订单状态',
    total_amount DECIMAL(10, 2) COMMENT '总金额',
    created_time DATETIME COMMENT '创建时间',
    updated_time DATETIME COMMENT '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='订单表';

-- 订单明细表（order_items）
DROP TABLE IF EXISTS shop_order_items;
CREATE TABLE shop_order_items
(
    item_id      BIGINT PRIMARY KEY COMMENT '明细ID',
    order_id     BIGINT COMMENT '订单ID',
    product_id   BIGINT COMMENT '商品ID',
    quantity     INT COMMENT '数量',
    subtotal     DECIMAL(10, 2) COMMENT '小计金额',
    created_time DATETIME COMMENT '创建时间',
    updated_time DATETIME COMMENT '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='订单明细表';


-- 向商品分类表插入默认数据
INSERT INTO shop_categories (category_id, category_name, created_time, updated_time)
VALUES (1, '电子产品', NOW(), NOW()),
       (2, '家居用品', NOW(), NOW()),
       (3, '服装鞋帽', NOW(), NOW()),
       (4, '食品饮料', NOW(), NOW()),
       (5, '图书杂志', NOW(), NOW());

-- 向商品表插入默认数据
INSERT INTO shop_products (product_id, category_id, product_name, description, price, stock_quantity, image_url,
                           created_time, updated_time)
VALUES (1, 1, '手机', '智能手机', 799.99, 100, 'phone.jpg', NOW(), NOW()),
       (2, 2, '沙发', '舒适沙发', 499.99, 50, 'sofa.jpg', NOW(), NOW()),
       (3, 3, 'T恤', '时尚T恤', 29.99, 200, 'tshirt.jpg', NOW(), NOW()),
       (4, 4, '巧克力', '美味巧克力', 5.99, 300, 'chocolate.jpg', NOW(), NOW()),
       (5, 5, '编程入门', '编程基础教程', 19.99, 100, 'programming.jpg', NOW(), NOW());

-- 向购物车表插入默认数据
INSERT INTO shop_carts (cart_id, user_id)
VALUES (1, 101),
       (2, 102),
       (3, 103),
       (4, 104),
       (5, 105);

-- 向购物车明细表插入默认数据
INSERT INTO shop_cart_items (item_id, cart_id, product_id, quantity, subtotal, created_time, updated_time)
VALUES (1, 1, 1, 2, 1599.98, NOW(), NOW()),
       (2, 2, 3, 3, 89.97, NOW(), NOW()),
       (3, 3, 2, 1, 499.99, NOW(), NOW()),
       (4, 4, 5, 2, 39.98, NOW(), NOW()),
       (5, 5, 4, 5, 29.95, NOW(), NOW());

-- 向订单表插入默认数据
INSERT INTO shop_orders (order_id, user_id, order_date, order_status, total_amount, created_time, updated_time)
VALUES (1, 101, '2023-08-01 10:30:00', '已支付', 1599.98, NOW(), NOW()),
       (2, 102, '2023-08-02 11:15:00', '已支付', 89.97, NOW(), NOW()),
       (3, 103, '2023-08-03 14:20:00', '待支付', 499.99, NOW(), NOW()),
       (4, 104, '2023-08-04 09:45:00', '已支付', 39.98, NOW(), NOW()),
       (5, 105, '2023-08-05 16:05:00', '已支付', 29.95, NOW(), NOW());

-- 向订单明细表插入默认数据
INSERT INTO shop_order_items (item_id, order_id, product_id, quantity, subtotal, created_time, updated_time)
VALUES (1, 1, 1, 2, 1599.98, NOW(), NOW()),
       (2, 2, 3, 3, 89.97, NOW(), NOW()),
       (3, 3, 2, 1, 499.99, NOW(), NOW()),
       (4, 4, 5, 2, 39.98, NOW(), NOW()),
       (5, 5, 4, 5, 29.95, NOW(), NOW());


-- 创建表：promocodes (兑换码表)
drop table if exists shop_promocodes;
create table shop_promocodes
(
    promo_code_id   bigint primary key comment '兑换码id',
    coupon_id       bigint comment '关联的优惠券id (可选)',
    shop_id         bigint comment '店铺id,表示使用对象,为空则是<通用>兑换码(可选)',
    promo_code      varchar(255) not null comment '兑换码,key开头',
    is_used         tinyint      not null comment '是否已使用 (0 - 未使用, 1 - 已使用)',
    gen_count       varchar(64)  not null comment '生成批次,gen开头',
    expiration_date datetime         not null comment '有效期',
    created_time datetime comment '创建时间',
    updated_time datetime comment '更新时间'
);

-- 创建表：coupons (优惠券表)
drop table if exists shop_coupons;
create table shop_coupons
(
    coupon_id           bigint primary key comment '优惠券id',
    coupon_type         varchar(50)    not null comment '优惠券类型 (1满减、2直减、3直接白送等)',
    coupon_amount       decimal(10, 2) not null comment '优惠金额',
    min_purchase_amount decimal(10, 2) not null comment '最小购买金额要求',
    expiration_date     datetime           not null comment '有效期',
    other_coupon_info   text comment '其他优惠券相关信息'
);

-- 创建表：usercoupons (用户优惠券表)
drop table if exists shop_user_coupons;
create table shop_user_coupons
(
    user_coupon_id bigint primary key comment '用户优惠券id',
    user_id        bigint  not null comment '用户id',
    coupon_id      bigint  not null comment '优惠券id',
    is_used        tinyint not null comment '是否已使用 (0 - 未使用, 1 - 已使用)',
    usage_date     datetime comment '使用日期 (可选)'
);


-- 插入测试数据到 shop_promocodes 表
INSERT INTO shop_promocodes (promo_code_id, coupon_id, shop_id, promo_code, is_used, gen_count, expiration_date, created_time, updated_time)
VALUES
    (1, 1, 1, 'KEY123', 0, 'GEN001', '2023-12-31 00:00:00', NOW(), NOW()),
    (2, 2, 2, 'KEY456', 0, 'GEN002', '2024-06-30 00:00:00', NOW(), NOW()),
    (3, 3, NULL, 'KEY789', 1, 'GEN003', '2023-09-15 00:00:00', NOW(), NOW());

-- 插入测试数据到 shop_coupons 表
INSERT INTO shop_coupons (coupon_id, coupon_type, coupon_amount, min_purchase_amount, expiration_date, other_coupon_info)
VALUES
    (1, '1满减', 10.00, 50.00, '2023-12-31 00:00:00', '购满50减10优惠券'),
    (2, '2直减', 5.00, 0.00, '2024-06-30 00:00:00', '直减5元优惠券'),
    (3, '3直接白送', 0.00, 20.00, '2023-09-15 00:00:00', '满20元送产品优惠券');

-- 插入测试数据到 shop_user_coupons 表
INSERT INTO shop_user_coupons (user_coupon_id, user_id, coupon_id, is_used, usage_date)
VALUES
    (1, 101, 1, 0, NULL),
    (2, 102, 2, 0, NULL),
    (3, 103, 3, 1, '2023-08-10 00:00:00');


