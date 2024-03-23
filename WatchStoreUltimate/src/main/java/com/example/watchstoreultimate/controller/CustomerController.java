package com.example.watchstoreultimate.controller;

import com.example.watchstoreultimate.constant.RoleConstant;
import com.example.watchstoreultimate.constant.UrlConstant;
import com.example.watchstoreultimate.dto.request.CustomerRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Customer;
import com.example.watchstoreultimate.service.CustomerService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping(UrlConstant.CustomerURL.PRE_FIX)
public class CustomerController {
    @Autowired
    CustomerService customerService ;

    @RequestMapping(value = UrlConstant.CustomerURL.FIND_ALL , method = RequestMethod.GET)
    public ResponseEntity<?> getCustomer(@RequestParam(defaultValue = "1") int pageIndex ,
                                         @RequestParam(defaultValue = "9") int pageSize){
        Pageable pageable = PageRequest.of(pageIndex - 1  , pageSize) ;
        Response response = customerService.getCustomer(pageable) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.CustomerURL.FIND_BY_ID, method = RequestMethod.GET)
    public ResponseEntity<?> findCustomerById(@PathVariable int customerId){
        Response response = customerService.findCustomerById(customerId) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addCustomer(@RequestBody @Valid CustomerRequest request){
        Response response = customerService.addCustomer(request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updCustomer(@RequestBody @Valid Customer customer){
        Response response = customerService.updCustomer(customer) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.CustomerURL.DEL_CUSTOMER ,method = RequestMethod.DELETE)
    public ResponseEntity<?> delCustomer(@PathVariable List<Integer> customerIds){
        Response response = customerService.delCustomer(customerIds) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.CustomerURL.DEL_CUSTOMER_BY_ADMIN, method = RequestMethod.DELETE)
    public ResponseEntity<?> delCustomerNoChange(@PathVariable List<Integer> customerIds){
        Response response = customerService.delCustomerNoChange(customerIds) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.CustomerURL.SEND_REQUEST_CONFIRM_EMAIL ,method = RequestMethod.POST)
    public ResponseEntity<?> sendMail(@RequestBody @Valid CustomerRequest request) throws MessagingException, UnsupportedEncodingException {
        Response response = customerService.sendCode(request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.CustomerURL.AUTH_REQUEST_CONFIRM_EMAIL, method = RequestMethod.POST)
    public ResponseEntity<?> authCode(@RequestBody @Valid CustomerRequest request,
                                      @PathVariable int code){
        Response response = customerService.authCode(request , code) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }
}
