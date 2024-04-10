package com.example.watchstoreultimate.service;

import com.example.watchstoreultimate.dto.request.AccountRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Account;
import jakarta.mail.MessagingException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface AccountService extends UserDetailsService {
    Response signIn(String userName, String password) ;
    Response refreshToken(String refreshToken) ;
    Response addAccount(AccountRequest request) ;
    Response updAccount(int accountId , AccountRequest request) ;
    Response forgetPassword(String customerEmail) throws MessagingException, UnsupportedEncodingException;
    Response authCode(String customerEmail , int code) ;
}
