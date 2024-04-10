package com.example.watchstoreultimate.service.impl;

import com.example.watchstoreultimate.entity.Account;
import com.example.watchstoreultimate.exception.AppException;
import com.example.watchstoreultimate.exception.ErrorCode;
import com.example.watchstoreultimate.repository.TokenRepository;
import com.example.watchstoreultimate.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {
    @Autowired
    private TokenRepository repository ;
    static String KEY  = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    private Key getSigningKey(){
        byte[] keys = Decoders.BASE64.decode(KEY) ;
        return Keys.hmacShaKeyFor(keys) ;
    }
    @Override
    public String generateToken(Account account) {
        return  Jwts.builder()
                .setSubject(account.getUsername())
                .setIssuer(account.getCustomer().getCustomerName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EFFECTIVE))
                .signWith( SignatureAlgorithm.HS256, getSigningKey())
                .compact() ;
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);

    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsFunction) {
        final Claims claims = extractAllClaim(token) ;
        return claimsFunction.apply(claims);
    }

    @Override
    public Claims extractAllClaim(String token){
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }catch(Exception e){
            return null ;
        }
    }

    @Override
    public boolean isTokenValid(String token, Account account) {
        String userName = extractUsername(token) ;
        return userName.equals(account.getUsername()) && isTokenExpired(token) && isTokenExist(token , account);
    }

    @Override
    public boolean isTokenExpired(String token) {
        Date date = extractClaim(token , Claims::getExpiration) ;
        return date.after(new Date(System.currentTimeMillis()));
    }

    @Override
    public boolean isTokenExist(String token, Account account) {
        return repository.existsByTokenContentAndAccount(token,account);
    }
}
