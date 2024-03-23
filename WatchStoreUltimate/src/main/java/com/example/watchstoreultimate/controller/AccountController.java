package com.example.watchstoreultimate.controller;

import com.example.watchstoreultimate.constant.UrlConstant;
import com.example.watchstoreultimate.dto.request.AccountRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.service.AccountService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping(UrlConstant.AccountURL.PRE_FIX)
public class AccountController {


    @Autowired
    AccountService accountService ;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addAccount(@RequestBody @Valid AccountRequest request){
        Response response = accountService.addAccount(request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.AccountURL.SIGN_IN , method = RequestMethod.POST)
    public ResponseEntity<?> signIn(@PathVariable String userName,
                                    @PathVariable String password){
        Response response= accountService.signIn(userName ,password) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }


    @RequestMapping(value = UrlConstant.AccountURL.CHANGE_PASSWORD , method = RequestMethod.PUT)
    public ResponseEntity<?> changePassword(@PathVariable int accountId ,
                                            @RequestBody @Valid AccountRequest request){
        Response response = accountService.updAccount(accountId, request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.AccountURL.FORGET_PASSWORD , method = RequestMethod.GET)
    public ResponseEntity<?> forgetPassword(@PathVariable String customerEmail) throws MessagingException, UnsupportedEncodingException {
        Response response = accountService.forgetPassword(customerEmail) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }


    @RequestMapping(value = UrlConstant.AccountURL.FORGET_PASSWORD_AUTH, method = RequestMethod.GET)
    public ResponseEntity<?> authCode(@PathVariable String customerEmail,
                                      @PathVariable int code){
        Response response = accountService.authCode(customerEmail, code) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }




}
