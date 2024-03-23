package com.example.watchstoreultimate.service.impl;

import com.example.watchstoreultimate.entity.Account;
import com.example.watchstoreultimate.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {
    static final String KEY ="67236723678126737912863789012083b4371283678213gfvc321783781276837862176376236857126783hvgd467127231hdasgr62783h21673762" ;
    private Key getSigningKey(){
        byte[] keys = Decoders.BASE64.decode(KEY) ;
        return Keys.hmacShaKeyFor(keys) ;
    }
    @Override
    public String generateToken(Account account) {
        return Jwts.builder()
                .setSubject(account.getUsername())
                .setIssuer(account.getCustomer().getCustomerName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EFFECTIVE))
                .signWith(getSigningKey())
                .compact() ;
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token , Claims::getSubject);
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsFunction) {
        Claims claims = extractAllClaim(token) ;
        return claimsFunction.apply(claims);
    }

    @Override
    public Claims extractAllClaim(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public boolean isTokenValid(String token, Account account) {
        String userName = extractUsername(token) ;
        return userName.equals(account.getUsername()) && isTokenExpired(token);
    }

    @Override
    public boolean isTokenExpired(String token) {
        Date date = extractClaim(token , Claims::getExpiration) ;
        return date.after(new Date(System.currentTimeMillis()));
    }
}
