package com.example.watchstoreultimate.service.impl;

import com.example.watchstoreultimate.dto.request.AccountRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Account;
import com.example.watchstoreultimate.entity.Customer;
import com.example.watchstoreultimate.entity.Role;
import com.example.watchstoreultimate.exception.AppException;
import com.example.watchstoreultimate.exception.ErrorCode;
import com.example.watchstoreultimate.mapper.AccountMapper;
import com.example.watchstoreultimate.repository.AccountRepository;
import com.example.watchstoreultimate.repository.CustomerRepository;
import com.example.watchstoreultimate.repository.RoleRepository;
import com.example.watchstoreultimate.service.AccountService;
import com.example.watchstoreultimate.service.JwtService;
import com.example.watchstoreultimate.service.MailService;
import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Lazy
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountServiceImpl implements AccountService {
    @Autowired
    CustomerRepository customerRepository ;
    @Autowired
    RoleRepository roleRepository ;
    @Autowired
    AccountRepository accountRepository ;
    @Autowired
    AccountMapper accountMapper ;
    @Autowired @Lazy
    PasswordEncoder passwordEncoder ;
    @Autowired @Lazy
    AuthenticationManager authenticationManager ;
    @Autowired
    JwtService jwtService ;
    @Autowired
    MailService mailService ;

    @Override
    public Response signIn(String userName, String password) {
        Account account = accountRepository.findByUserNameAndAccountAvailable(userName, true).orElseThrow(
                () -> new AppException(ErrorCode.USERNAME_NOT_FOUNT)
        ) ;
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userName , password)
        ) ;
        String token = jwtService.generateToken(account);
        return Response.builder()
                .code(200)
                .result(token)
                .message("TOKEN")
                .build() ;


    }

    @Override
    public Response addAccount(AccountRequest request) {
        if(accountRepository.existsByUserName(request.getUserName())){
            throw new AppException(ErrorCode.USERNAME_EXISTED) ;
        }
        Role role = roleRepository.findById(request.getRoleId()).orElseThrow(
                ()-> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        ) ;
        Customer customer = customerRepository.findByCustomerIdAndCustomerAvailable(request.getCustomerId(), true).orElseThrow(
                ()-> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        ) ;
        if(accountRepository.existsByCustomer(customer)){
            throw  new RuntimeException("Người dùng đã có tài khoản") ;
        }

        Account account = accountMapper.toAccount(request) ;
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        account.setRole(role);
        account.setCustomer(customer);
        accountRepository.save(account) ;
        String token = jwtService.generateToken(account);
        return Response.builder()
                .code(200)
                .result(token)
                .message("TOKEN")
                .build();
    }

    @Override // Change password
    public Response updAccount(int accountId, AccountRequest request) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        );
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        accountRepository.save(account) ;
        return Response.builder()
                .code(200)
                .result(request.getUserName())
                .message("Change password success")
                .build();
    }



    @Override
    public Response forgetPassword(String customerEmail) throws MessagingException, UnsupportedEncodingException {
        mailService.sendMail(customerEmail) ;
        return Response.builder()
                .code(200)
                .result(customerEmail)
                .message("Send email success")
                .build();
    }

    @Override
    public Response authCode(String customerEmail, int code) {
        Response response = mailService.authCode(customerEmail , code) ;
        response.setResult(customerEmail);
        return response ;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUserNameAndAccountAvailable(username , true).orElseThrow(
                ()-> new AppException(ErrorCode.USERNAME_NOT_FOUNT)
        );
    }
}
