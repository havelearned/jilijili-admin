## 项目介绍

#### 主要特性

- 使用最新技术栈，社区资源丰富。
- 高效率开发，代码生成器可一键生成前后端代码
- 支持数据字典，可方便地对一些状态进行管理
- 支持接口级别的功能权限与数据权限，可自定义操作
- 自定义权限注解与匿名接口注解，可快速对接口拦截与放行
- 前后端统一异常拦截处理，统一输出异常，避免繁琐的判断

### 系统效果预览

![](https://picst.sunbangyan.cn/2023/10/17/50ee4d467ad5027abec85bcd34680328.png)
![](https://picst.sunbangyan.cn/2023/10/17/941e9ccae5440e7fb4e732041bdac525.png)
![](https://picst.sunbangyan.cn/2023/10/17/3668ba9084dae0d84e2a771b2614cb04.png)
![](https://picst.sunbangyan.cn/2023/10/17/b300c2dd34c2855db70e9663d9090e75.png)
![](https://picdl.sunbangyan.cn/2023/10/17/17e34f7902e485edff23435340c8d2e5.png)
![](https://picss.sunbangyan.cn/2023/10/17/a5624d86bc436a36da9969704f3ba7a1.png)
![](https://picdm.sunbangyan.cn/2023/10/17/08aa7e5a88a5a4a4cdb8359d208f7909.png)
![](https://picdm.sunbangyan.cn/2023/10/17/eee6c87f713696865a3a734b4ad834fe.png)
![](https://picss.sunbangyan.cn/2023/10/17/c441e6ae6c55a4e374165c5b3a59f82f.png)
![](https://picdl.sunbangyan.cn/2023/10/17/69e5ad41f22bc91b891b0ada0b966000.png)
![](https://picdm.sunbangyan.cn/2023/10/17/9a36a2d467308fa0059f383d5fda14e7.png)
![](https://picdl.sunbangyan.cn/2023/10/17/6f19d333126622ac52caed269e2509a6.png)
![](https://picss.sunbangyan.cn/2023/10/17/ac9b398aad30372a3c48d9526ac791a6.png)



### 后台目录结构

![](https://picdl.sunbangyan.cn/2023/10/17/c784dbb7db7a7031f0338872d62aaac8.png)

### 技术架构

#### 后端

- 基础框架：Spring Boot 3.0.0
- 微服务框架： Spring Cloud Alibaba 2022.0.0.0
- 持久层框架：MybatisPlus 3.5.1
- 报表工具： JimuReport 1.5.8
- 安全框架：Apache Shiro 1.10.0，Jwt 3.11.0 Sa-Token
- 微服务技术栈：Spring Cloud Alibaba、Nacos、Gateway、Sentinel、Skywalking
- 数据库连接池：阿里巴巴Druid 1.1.22
- 日志打印：logback
- 其他：autopoi, fastjson，poi，Swagger-ui apiFox，quartz, lombok等。

#### 前端

- 脚手架: Vue3 UI Quasar cli
- axios 1.4+
- stompjs 2.3+

#### 支持库

| 数据库         | 支持 |
| -------------- | ---- |
| MySQL          | √    |
| Oracle11g      | ×    |
| Sqlserver2017  | ×    |
| PostgreSQL     | ×    |
| MariaDB        | √    |
| 达梦、人大金仓 | ×    |

#### 微服务解决方案

```
1、服务注册和发现 Nacos √

2、统一配置中心 Nacos √

3、路由网关 gateway(三种加载方式) √

4、分布式 http feign √

5、~~熔断降级限流 Sentinel ~~√

6、分布式文件 Minio、阿里OSS √

7、统一权限控制 JWT + Shiro √

8、服务监控 SpringBootAdmin√

9、~~链路跟踪 Skywalking ~~[~~参考文档~~](https://gitee.com/link?target=https://help.jeecg.com/java/springcloud/super/skywarking.html)

10、消息中间件 RabbitMQ √

11、分布式任务 xxl-job √

12、~~分布式事务 Seata~~

13、~~分布式日志 elk + kafka~~

14、支持 docker-compose、~~k8s、jenkins~~

15、CAS 单点登录 √

16、路由限流 √
```

#### 微服务架构图

![](image\Snipaste_2023-10-16_17-19-03.png)



### 功能模块

```text
├─系统管理

│  ├─首站

│  ├─管理中心
│  │  ├─用户管理
│  │  ├─角色管理
│  │  ├─字典管理
│  │  ├─菜单管理
│  │  ├─系统工具
│  │  ├─通知记录
│  │  ├─系统监控

│  ├─资源管理
│  │  ├─文件列表


│  ├─商城中心
│  │  ├─仪表盘
│  │  ├─商品列表
│  │  ├─订单列表
│  │  ├─货币管理

│  ├─博客中心
│  │  ├─文章列表
│  │  ├─分类列表
│  │  ├─标签列表
│  │  ├─评论审核

│  ├─音乐鉴赏
│  ├─接口地址

├─代码生成器(低代码) (暂无业务)

└─其他模块

  └─更多功能开发中。。
```
