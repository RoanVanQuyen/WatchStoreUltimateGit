package com.example.watchstoreultimate.service.impl;

import com.example.watchstoreultimate.dto.request.AddressRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Address;
import com.example.watchstoreultimate.entity.Customer;
import com.example.watchstoreultimate.exception.AppException;
import com.example.watchstoreultimate.exception.ErrorCode;
import com.example.watchstoreultimate.mapper.AddressMapper;
import com.example.watchstoreultimate.repository.AddressRepository;
import com.example.watchstoreultimate.repository.CustomerRepository;
import com.example.watchstoreultimate.service.AddressService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressRepository addressRepository ;
    @Autowired
    CustomerRepository customerRepository ;
    @Autowired
    AddressMapper addressMapper ;
    @Override
    public Response addAddress(AddressRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow(
                ()  -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        );
        if(addressRepository.existsByCustomer(customer)){
            throw new AppException(ErrorCode.ERR_ADDRESS_EXISTED_FOR_CUSTOMER) ;
        }
        Address address = addressMapper.toAddress(request) ;
        address.setCustomer(customer);
        return Response.builder()
                .code(200)
                .result(addressRepository.save(address))
                .message("Add address success")
                .build();
    }

    @Override
    public Response updAddress(int addressId, AddressRequest request) {
        Address address = addressRepository.findById(addressId).orElseThrow(
                ()-> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        );
        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow(
                ()  -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        );
        address.setCustomer(customer);
        addressMapper.updAddress(address , request);
        return Response.builder()
                .code(200)
                .result(addressRepository.save(address))
                .message("Update address success")
                .build();
    }

    @Override
    public Response findById(int id) {
        Address address = addressRepository.findById(id).orElseThrow(
                ()-> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        );
        return Response.builder()
                .code(200)
                .result(address)
                .message("Find address success")
                .build();
    }

    @Override
    public Response findByCustomerId(int customerId) {
        Address address = addressRepository.findByCustomer(customerId).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        ) ;
        return Response.builder()
                .code(200)
                .result(address)
                .message("Find address success")
                .build();
    }
}
