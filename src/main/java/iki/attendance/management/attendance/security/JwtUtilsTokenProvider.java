package iki.attendance.management.attendance.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtilsTokenProvider {
    @Value("${app-jwt-secret}")
    private String jwtSecret;
    @Value(("${app-expiration-milliseconds}"))
    private Long expirationTime;

    //createKey
    private SecretKey keys(){
        return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtSecret));
    }

    //createToken
    public String generateToken(Authentication authentication){
        String username= authentication.getName();
        Date currentDate=new Date();
        return Jwts.builder()
                .subject(username)
                .issuedAt(currentDate)
                .expiration(new Date(currentDate.getTime()+expirationTime))
                .signWith(keys())
                .compact();
    }

    //getUsernameFromToken
    public String getUsernameFromToken(String token){
        return Jwts.parser()
                .verifyWith(keys())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    //validateToken
    public boolean validateToken(String token){
               Jwts.parser()
                .verifyWith(keys())
                .build()
                .parseSignedClaims(token);
               return true;
    }

}
