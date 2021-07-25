/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/
package com.skyhawks.authentication.config.jwt;

import com.skyhawks.authentication.ApplicationConstants;
import com.skyhawks.authentication.model.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTUtil {
    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(LoginUser user) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, user.getUuid().toString());
    }


    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, ApplicationConstants.SECRET_KEY).compact();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsTFunction){
        final Claims claims = extractAllClaim(token);
        return claimsTFunction.apply(claims);
    }

    private Claims extractAllClaim(String token){
        return Jwts.parser().setSigningKey(ApplicationConstants.SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public Boolean validateToken(String token, LoginUser userDetails){
        final  String userName = extractUserName(token);
        return (userName.equals(userDetails.getUserName()) && !isTokenExpired(token));
    }

}
