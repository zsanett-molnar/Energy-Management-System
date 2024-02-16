package com.energyapp.usermanagement.services;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import java.util.Date;
@Service
public class JwtService {
    private static final String secretKey = "aVeryLongStringThatContainsAMixOfUpperAndLowerCaseLettersNumbers1234567890AndSymbolsAABBCCDDEEFFGGHHIIJJKKLL";

    public String generateJwtToken(String username, Integer userId) {

        long expirationMillis = 86400000;

        return Jwts.builder()
                .setSubject(username)
                .setId(String.valueOf(userId))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public static boolean isTokenValid(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();

            return !claims.getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT Token is expired!");
            return false;
        } catch (SignatureException e) {
            System.out.println("Signature does not match the secret key");
            return false;
        }
    }

}
