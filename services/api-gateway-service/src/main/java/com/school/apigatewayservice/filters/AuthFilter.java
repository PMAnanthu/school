package com.school.apigatewayservice.filters;

import com.school.apigatewayservice.dto.TokenResponse;
import com.school.apigatewayservice.dto.ValidateJWT;
import com.school.apigatewayservice.proxy.AuthProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    @Autowired
    private  AuthProxy authProxy;

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                throw new RuntimeException("Missing authorization information");
            }

            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

            String[] parts = authHeader.split(" ");

            if (parts.length != 2 || !"Bearer".equals(parts[0])) {
                throw new RuntimeException("Incorrect authorization structure");
            }
            ServerHttpRequest.Builder builder = exchange.getRequest().mutate();
            TokenResponse response = authProxy.validateToken(new ValidateJWT(parts[1]));
            if(response!=null) {
                builder.header("X-auth-user-id", String.valueOf(response.getUserId()));
            }else{
                throw new RuntimeException("Login failed");
            }
            return chain.filter(exchange.mutate().request(builder.build()).build());
        };
    }

    public static class Config {

    }
}