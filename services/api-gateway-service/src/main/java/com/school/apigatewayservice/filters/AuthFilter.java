package com.school.apigatewayservice.filters;

import com.school.apigatewayservice.dto.TokenResponse;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    private final RestTemplate restTemplate;

    public AuthFilter(RestTemplate restTemplate) {
        super(Config.class);
        this.restTemplate = restTemplate;
    }

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
            TokenResponse userDto = restTemplate.getForObject(String.format("%s//validateToken?token=%s",
                    "authentication-service", parts[1]), TokenResponse.class);
            if(userDto!=null) {
                builder.header("X-auth-user-id", String.valueOf(userDto.getUserId()));
            }else{
                throw new RuntimeException("Login failed");
            }
            return chain.filter(exchange.mutate().request(builder.build()).build());
        };
    }

    public static class Config {

    }
}