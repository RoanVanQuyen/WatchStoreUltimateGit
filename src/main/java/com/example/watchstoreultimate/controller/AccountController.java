package com.example.watchstoreultimate.controller;

import com.example.watchstoreultimate.constant.ErrorResponseCode;
import com.example.watchstoreultimate.constant.ErrorResponseMessage;
import com.example.watchstoreultimate.constant.UrlConstant;
import com.example.watchstoreultimate.dto.request.AccountRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping(UrlConstant.AccountURL.PRE_FIX)
@Tag( name = "ACCOUNT API" , description = "REST APIs FOR ACCOUNT CONTROLLER TO RELATED TO PERFORM USER OPERATIONS ")
public class AccountController {

    @Autowired
    AccountService accountService ;

    @Operation(summary = "ADD NEW ACCOUNT" , description = "Method = POST, Object=[ O.AccountRequest ]" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = ErrorResponseCode.Account.USERNAME_EXISTED +"", description = ErrorResponseMessage.Account.USERNAME_EXISTED , content = @Content)
            , @ApiResponse(responseCode = ErrorResponseCode.ERR_ID_NOT_FOUND + "" , description = ErrorResponseMessage.ERR_ID_NOT_FOUND)
    })
    @RequestMapping(value = UrlConstant.AccountURL.REGISTER , method = RequestMethod.POST)
    public ResponseEntity<?> addAccount(@RequestBody @Valid AccountRequest request){
        Response response = accountService.addAccount(request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    // clean architechture
    @Operation(summary = "SIGN IN SYSTEM" , description = "Method = POST, Object = [ S.userName, S.password ] ")
    @ApiResponse(responseCode = ErrorResponseCode.Account.USERNAME_NOT_FOUND + "", description = ErrorResponseMessage.Account.USERNAME_NOT_FOUND , content = @Content)
    @RequestMapping(value = UrlConstant.AccountURL.SIGN_IN , method = RequestMethod.POST)
    public ResponseEntity<?> signIn(@PathVariable String userName,
                                    @PathVariable String password){
        Response response= accountService.signIn(userName ,password) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @Operation(summary = "CHANGE PASSWORD" , description = "Method = PUT, Object = [ I.accountId , O.AccountRequest ]")
    @ApiResponse(responseCode = ErrorResponseCode.ERR_ID_NOT_FOUND + "" , description = ErrorResponseMessage.ERR_ID_NOT_FOUND)
    @RequestMapping(value = UrlConstant.AccountURL.U_CHANGE_PASSWORD, method = RequestMethod.PUT)
    public ResponseEntity<?> changePassword(@PathVariable int accountId ,
                                            @RequestBody @Valid AccountRequest request){
        Response response = accountService.updAccount(accountId, request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }


    @Operation(summary = "FORGET PASSWORD" , description = "Method = GET, SendMail to User auth, Object = [ S.customerEmail ] ")
    @RequestMapping(value = UrlConstant.AccountURL.FORGET_PASSWORD , method = RequestMethod.GET)
    public ResponseEntity<?> forgetPassword(@PathVariable String customerEmail) throws MessagingException, UnsupportedEncodingException {
        Response response = accountService.forgetPassword(customerEmail) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @Operation(summary = "AUTH CODE EMAIL FOR FORGET PASSWORD" , description = "Method = GET, Object = [ S.customerEmail, I.code ]")
    @RequestMapping(value = UrlConstant.AccountURL.FORGET_PASSWORD_AUTH, method = RequestMethod.GET)
    public ResponseEntity<?> authCode(@PathVariable String customerEmail,
                                      @PathVariable int code){
        Response response = accountService.authCode(customerEmail, code) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @Operation(summary = "Refresh token")
    @RequestMapping(value = UrlConstant.AccountURL.REFRESH_TOKEN, method = RequestMethod.POST)
    public ResponseEntity<?> refreshToken(@PathVariable String refreshToken){
        Response response = accountService.refreshToken(refreshToken) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }




}
