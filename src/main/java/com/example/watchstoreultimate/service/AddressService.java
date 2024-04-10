package com.example.watchstoreultimate.service;

import com.example.watchstoreultimate.dto.request.AddressRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Address;

public interface AddressService {
    Response addAddress(AddressRequest request) ;
    Response updAddress(int addressId , AddressRequest request) ;
    Response findById(int id) ;
    Response findByCustomerId(int customerId) ;
}
