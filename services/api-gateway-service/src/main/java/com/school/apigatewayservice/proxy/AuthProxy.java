package com.school.apigatewayservice.proxy;

import com.school.apigatewayservice.dto.TokenResponse;
import com.school.apigatewayservice.dto.ValidateJWT;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="authentication-service")
public interface AuthProxy {

    @PostMapping("/validate")
    TokenResponse validateToken(@RequestBody ValidateJWT validateJWT);
}
