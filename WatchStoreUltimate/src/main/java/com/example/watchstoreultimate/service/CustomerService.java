package com.example.watchstoreultimate.service;

import com.example.watchstoreultimate.dto.request.CustomerRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Customer;
import jakarta.mail.MessagingException;
import org.springframework.data.domain.Pageable;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface CustomerService {
    final static boolean CUSTOMER_AVAILABLE = true;
    final static boolean CUSTOMER_AVAILABLE_FALSE = false ;
    Response getCustomer(Pageable pageable) ;
    Response findCustomerById(int customerId) ;
    Response addCustomer(CustomerRequest request) ;
    Response updCustomer(Customer customer) ;
    Response delCustomer(List<Integer> customerIds) ;
    Response delCustomerNoChange(List<Integer> customerIds) ;
    Response sendCode(CustomerRequest request) throws MessagingException, UnsupportedEncodingException;
    Response authCode(CustomerRequest request , int code) ;
}
