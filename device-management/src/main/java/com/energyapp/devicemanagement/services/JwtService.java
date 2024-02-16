package com.energyapp.devicemanagement.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private static final String secretKey = "aVeryLongStringThatContainsAMixOfUpperAndLowerCaseLettersNumbers1234567890AndSymbolsAABBCCDDEEFFGGHHIIJJKKLL";

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
