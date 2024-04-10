package com.example.watchstoreultimate.mapper;

import com.example.watchstoreultimate.dto.request.AccountRequest;
import com.example.watchstoreultimate.entity.Account;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-08T01:12:35+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public Account toAccount(AccountRequest request) {
        if ( request == null ) {
            return null;
        }

        Account.AccountBuilder account = Account.builder();

        account.userName( request.getUserName() );
        account.password( request.getPassword() );

        return account.build();
    }

    @Override
    public Account updAccount(Account account, AccountRequest request) {
        if ( request == null ) {
            return account;
        }

        account.setUserName( request.getUserName() );
        account.setPassword( request.getPassword() );

        return account;
    }
}
