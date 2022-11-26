package com.unified.service.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
/**
 * This is utility  class related to jwt Token extraction, validation and creation
 * for more info  check
 * https://www.javainuse.com/webseries/spring-security-jwt/chap4
 * https://www.baeldung.com/java-json-web-tokens-jwt
 * @author rsarada
 */

@Service
public class JwtUtils {

    private String SECRET_KEY = "secretKey"; //env.getProperty("SECRET_KEY");

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return  extractClaim(token, Claims::getExpiration);
    }

    public  <T> T extractClaim(String token, Function<Claims,T>claimsResolver) {
        final  Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public  String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    public  String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*10)) // set expiration date 10 hours
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();

    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
