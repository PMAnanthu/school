package com.school.apigatewayservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Predicate;

@Configuration
public class ApiGatewayConfiguration<RouterValidator> {
    private List<String> openApiEndpoints = List.of(
            "/login"
    );
    @Autowired
    private JWTUtil jwtUtil;

    @Bean
    public WebClient.Builder getBuilder() {
        return WebClient.builder();
    }

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder, WebClient.Builder webBuilder) {
        Predicate<ServerHttpRequest> isSecured =
                request -> openApiEndpoints
                        .stream()
                        .noneMatch(uri -> request.getURI().getPath().contains(uri));
        GatewayFilter filter = (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if (isSecured.test(request)) {
                if (isAuthMissing(request))
                    return onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);

                final String[] token = getAuthHeader(request).split(" ");
                if (jwtUtil.isInvalid(token[1]))
                    return onError(exchange, "Authorization header is invalid", HttpStatus.UNAUTHORIZED);

                populateRequestWithHeaders(exchange, token[1]);
            }
            return chain.filter(exchange);
        };
        return builder.routes()
                .route(p -> p
                        .path("/login")
                        .uri("lb://authentication-service/login"))
                .route(p -> p
                        .path("/sign-up")
                        .uri("lb://authentication-service/sign-up"))
                .route(p -> p.path("/admin/**")
                        .uri("lb://user-management-service/admin/**"))
                .route(p -> p.path("/test")
                        .filters(f -> f.filter(filter))
                        .uri("lb://authentication-service/test"))
                .route(p -> p.path("/users/**","/students/**","/teachers/**","/parents/**","/schools/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://user-management-service/**"))
                .build();
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0);
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
        String userId = jwtUtil.extractUserId(token);
        exchange.getRequest().mutate()
                .header("userId", userId)
                .build();
    }

}