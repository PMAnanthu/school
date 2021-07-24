/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/
package com.skyhawks.authentication.services;

import com.skyhawks.authentication.config.ApplicationConfiguration;
import com.skyhawks.authentication.config.jwt.JWTUtil;
import com.skyhawks.authentication.model.LoginUser;
import com.skyhawks.authentication.repos.IUserRepo;
import com.skyhawks.authentication.request.CreateUserRequest;
import com.skyhawks.authentication.request.JWTTokenRequest;
import com.skyhawks.authentication.request.TokenResponse;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Data
@Service
public class UserService {

    private static final String MAIL_SEND_UPDATE_USER = "/mail/send/update-user";
    private final IUserRepo iUserRepo;

    private final BCryptPasswordEncoder passwordEncoder;

    private final JWTUtil jwtUtil;

    private final RestTemplate restTemplate;

    private final ApplicationConfiguration applicationConfiguration;

    public LoginUser findByUsername(String userName) {
        return iUserRepo.findByUserName(userName);
    }


    public LoginUser saveUser(CreateUserRequest request) {
        LoginUser loginUser = new LoginUser();
        loginUser.setUserName(request.getUserName());
        loginUser.setPassword(passwordEncoder.encode(request.getPassword()));
        loginUser.setNewUser(true);
        loginUser.setAccountNonExpired(true);
        loginUser.setAccountNonLocked(true);
        loginUser.setCredentialsNonExpired(true);
        loginUser.setEnabled(true);
        loginUser =iUserRepo.save(loginUser);
        loginUser.setPassword(request.getPassword());
        return loginUser;
    }

    public boolean isValidUser(String userId) {
       return iUserRepo.findById(UUID.fromString(userId)).isPresent();
    }


    public TokenResponse validate(JWTTokenRequest request) {
        final String token=request.getJwt().substring(7);
        String userName=jwtUtil.extractUserName(token);
        LoginUser user = iUserRepo.findByUserName(userName);
        if(jwtUtil.validateToken(token,user)){
            return new TokenResponse(user.getUuid().toString(),user.getUserName());
        }
        return null;
    }
}
