-- 商品分类表（categories）
DROP TABLE IF EXISTS shop_categories;
CREATE TABLE shop_categories
(
    category_id   INT PRIMARY KEY COMMENT '分类ID',
    category_name VARCHAR(255) COMMENT '分类名称',
    created_time  DATETIME COMMENT '创建时间',
    updated_time  DATETIME COMMENT '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='商品分类表';

-- 商品表（products）
DROP TABLE IF EXISTS shop_products;
CREATE TABLE shop_products
(
    product_id     INT PRIMARY KEY COMMENT '商品ID',
    category_id    INT COMMENT '分类ID',
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
    cart_id      INT PRIMARY KEY COMMENT '购物车ID',
    user_id      INT COMMENT '用户ID',
    created_time DATETIME COMMENT '创建时间',
    updated_time DATETIME COMMENT '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='购物车表';

-- 购物车明细表（cart_items）
DROP TABLE IF EXISTS shop_cart_items;
CREATE TABLE shop_cart_items
(
    item_id      INT PRIMARY KEY COMMENT '明细ID',
    cart_id      INT COMMENT '购物车ID',
    product_id   INT COMMENT '商品ID',
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
    order_id     INT PRIMARY KEY COMMENT '订单ID',
    user_id      INT COMMENT '用户ID',
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
    item_id      INT PRIMARY KEY COMMENT '明细ID',
    order_id     INT COMMENT '订单ID',
    product_id   INT COMMENT '商品ID',
    quantity     INT COMMENT '数量',
    subtotal     DECIMAL(10, 2) COMMENT '小计金额',
    created_time DATETIME COMMENT '创建时间',
    updated_time DATETIME COMMENT '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='订单明细表';


-- 向商品分类表插入默认数据
INSERT INTO shop_categories (category_id, category_name, created_time, updated_time)
VALUES
    (1, '电子产品', NOW(), NOW()),
    (2, '家居用品', NOW(), NOW()),
    (3, '服装鞋帽', NOW(), NOW()),
    (4, '食品饮料', NOW(), NOW()),
    (5, '图书杂志', NOW(), NOW());

-- 向商品表插入默认数据
INSERT INTO shop_products (product_id, category_id, product_name, description, price, stock_quantity, image_url, created_time, updated_time)
VALUES
    (1, 1, '手机', '智能手机', 799.99, 100, 'phone.jpg', NOW(), NOW()),
    (2, 2, '沙发', '舒适沙发', 499.99, 50, 'sofa.jpg', NOW(), NOW()),
    (3, 3, 'T恤', '时尚T恤', 29.99, 200, 'tshirt.jpg', NOW(), NOW()),
    (4, 4, '巧克力', '美味巧克力', 5.99, 300, 'chocolate.jpg', NOW(), NOW()),
    (5, 5, '编程入门', '编程基础教程', 19.99, 100, 'programming.jpg', NOW(), NOW());

-- 向购物车表插入默认数据
INSERT INTO shop_carts (cart_id, user_id, created_time, updated_time)
VALUES
    (1, 101, NOW(), NOW()),
    (2, 102, NOW(), NOW()),
    (3, 103, NOW(), NOW()),
    (4, 104, NOW(), NOW()),
    (5, 105, NOW(), NOW());

-- 向购物车明细表插入默认数据
INSERT INTO shop_cart_items (item_id, cart_id, product_id, quantity, subtotal, created_time, updated_time)
VALUES
    (1, 1, 1, 2, 1599.98, NOW(), NOW()),
    (2, 2, 3, 3, 89.97, NOW(), NOW()),
    (3, 3, 2, 1, 499.99, NOW(), NOW()),
    (4, 4, 5, 2, 39.98, NOW(), NOW()),
    (5, 5, 4, 5, 29.95, NOW(), NOW());

-- 向订单表插入默认数据
INSERT INTO shop_orders (order_id, user_id, order_date, order_status, total_amount, created_time, updated_time)
VALUES
    (1, 101, '2023-08-01 10:30:00', '已支付', 1599.98, NOW(), NOW()),
    (2, 102, '2023-08-02 11:15:00', '已支付', 89.97, NOW(), NOW()),
    (3, 103, '2023-08-03 14:20:00', '待支付', 499.99, NOW(), NOW()),
    (4, 104, '2023-08-04 09:45:00', '已支付', 39.98, NOW(), NOW()),
    (5, 105, '2023-08-05 16:05:00', '已支付', 29.95, NOW(), NOW());

-- 向订单明细表插入默认数据
INSERT INTO shop_order_items (item_id, order_id, product_id, quantity, subtotal, created_time, updated_time)
VALUES
    (1, 1, 1, 2, 1599.98, NOW(), NOW()),
    (2, 2, 3, 3, 89.97, NOW(), NOW()),
    (3, 3, 2, 1, 499.99, NOW(), NOW()),
    (4, 4, 5, 2, 39.98, NOW(), NOW()),
    (5, 5, 4, 5, 29.95, NOW(), NOW());
