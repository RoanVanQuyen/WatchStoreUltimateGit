package com.example.watchstoreultimate.service;

import com.example.watchstoreultimate.entity.Account;
import io.jsonwebtoken.Claims;

import java.util.function.Function;

public interface JwtService {
    static final int EFFECTIVE= 1000 * 60 * 3 ;
    String generateToken(Account account) ;
    String extractUsername(String token) ;
    <T> T extractClaim(String token, Function<Claims, T> claimsFunction) ;
    Claims extractAllClaim(String token) ;
    boolean isTokenValid(String token , Account account) ;
    boolean isTokenExpired(String token) ;
}
