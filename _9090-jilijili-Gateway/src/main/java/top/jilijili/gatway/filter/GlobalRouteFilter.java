package top.jilijili.gatway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

// 添加路由才会执行
@Component
@Slf4j
public class GlobalRouteFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String reqUrl = request.getRemoteAddress().getHostString();
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        MultiValueMap<String, HttpCookie> cookies = request.getCookies();


        log.info("请求地址:{}\n 请求参数:{}\n cookies:{}",
                reqUrl, queryParams.toString(), cookies.toString());
        Mono<Void> filter = chain.filter(exchange);
        ServerHttpResponse response = exchange.getResponse();
        HttpStatusCode statusCode = response.getStatusCode();
        log.info("响应状态:{}", statusCode);
        return filter;
    }


}
