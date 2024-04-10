package com.example.watchstoreultimate.mapper;

import com.example.watchstoreultimate.dto.request.AddressRequest;
import com.example.watchstoreultimate.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address toAddress(AddressRequest request) ;
    void updAddress(@MappingTarget Address address ,  AddressRequest request) ;
}