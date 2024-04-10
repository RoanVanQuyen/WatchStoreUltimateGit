package com.example.watchstoreultimate.mapper;

import com.example.watchstoreultimate.dto.request.AccountRequest;
import com.example.watchstoreultimate.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toAccount(AccountRequest request) ;
    Account updAccount(@MappingTarget Account account , AccountRequest request) ;
}
