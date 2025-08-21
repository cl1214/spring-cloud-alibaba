package com.cl.filter;

import com.auth0.jwt.interfaces.Claim;
import com.cl.properties.WhitePathProperties;
import com.cl.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
@EnableConfigurationProperties(WhitePathProperties.class)
public class AuthGlobalFilter implements GlobalFilter {

    @Autowired
    private WhitePathProperties whitePathProperties;

    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        List<String> whitePath = whitePathProperties.getWhitePath();
        if (!CollectionUtils.isEmpty(whitePath) && whitePath.contains(request.getPath().toString())) {
            return chain.filter(exchange);
        }
        String token = request.getHeaders().getFirst("Authorization");
        if (StringUtils.isEmpty(token)) {
            return writeException(exchange, HttpStatus.UNAUTHORIZED.value(), "鉴权失败");
        }
        Map<String, Claim> tokenInfo = JwtUtils.getTokenInfo(token);
        if (StringUtils.isEmpty(CollectionUtils.isEmpty(tokenInfo))) {
            return writeException(exchange, HttpStatus.UNAUTHORIZED.value(), "鉴权失败");
        }
        ServerWebExchange newExchange = exchange.mutate().request(builder -> {
            builder.header("userId", tokenInfo.get("userId").asString());
            builder.header("userName", "userName", tokenInfo.get("userName").asString());
        }).build();
        return chain.filter(newExchange);
    }

    private Mono<Void> writeException(ServerWebExchange exchange, int code, String message) {
        ServerHttpResponse response = exchange.getResponse();
        response.setRawStatusCode(code);
        return response.writeWith(Mono.just(response.bufferFactory().wrap(message.getBytes())));
    }
}
