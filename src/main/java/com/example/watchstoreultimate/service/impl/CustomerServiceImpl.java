package com.example.watchstoreultimate.service.impl;

import com.example.watchstoreultimate.dto.request.CustomerRequest;
import com.example.watchstoreultimate.dto.response.PageCustom;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Account;
import com.example.watchstoreultimate.entity.Comment;
import com.example.watchstoreultimate.entity.Customer;
import com.example.watchstoreultimate.exception.AppException;
import com.example.watchstoreultimate.exception.ErrorCode;
import com.example.watchstoreultimate.mapper.CustomerMapper;
import com.example.watchstoreultimate.repository.AccountRepository;
import com.example.watchstoreultimate.repository.CustomerRepository;
import com.example.watchstoreultimate.service.CustomerService;
import com.example.watchstoreultimate.service.MailService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository ;
    @Autowired
    AccountRepository accountRepository ;
    @Autowired
    CustomerMapper customerMapper ;
    @Autowired
    MailService mailService ;

    @Override
    public Response getCustomer(Pageable pageable) {
        Page<Customer> page = customerRepository.findAllByCustomerAvailable(CUSTOMER_AVAILABLE,pageable) ;
        PageCustom<Customer> pageCustom = PageCustom.<Customer>builder()
                .pageIndex(page.getNumber() + 1)
                .pageElement(page.getSize())
                .pageSize(page.getTotalPages())
                .content(page.getContent())
                .sort(page.getSort().toString())
                .build();
        return Response.builder()
                .code(200)
                .result(pageCustom)
                .message("Get customer success")
                .build();
    }

    @Override
    public Response findCustomerById(int customerId) {
        Customer customer = customerRepository.findByCustomerIdAndCustomerAvailable(customerId, CUSTOMER_AVAILABLE).orElseThrow(
                () ->new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        );
        return Response.builder()
                .code(200)
                .result(customer)
                .message("Find customer by id success")
                .build();
    }

    private boolean validate(String regex , String text){
        Pattern pattern = Pattern.compile(regex) ;
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
    @Override
    public Response addCustomer(CustomerRequest request) {
        String regexEmail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$" ;
        String regexPhone = "^(84|0[3|5|7|8|9])+([0-9]{8})$" ;
        if(!validate(regexEmail, request.getCustomerEmail()) || !validate(regexPhone,request.getCustomerPhone())){
            if(!validate( regexEmail, request.getCustomerEmail())) {
                throw new AppException(ErrorCode.ERR_CUSTOMER_EMAIL_VALID) ;
            }
            else {
                if (!validate(regexPhone, request.getCustomerPhone())) {
                    throw new AppException(ErrorCode.ERR_CUSTOMER_PHONE_VALID);
                }
            }
        }
        Customer customer = customerMapper.toCustomer(request) ;
        if(!customerRepository.existsByCustomerEmailOrCustomerPhone(customer.getCustomerEmail(), customer.getCustomerPhone())){
            return Response.builder()
                    .code(200)
                    .result(customerRepository.save(customer))
                    .message("Add customer success")
                    .build() ;
        }else{
            if(customerRepository.existsByCustomerEmail(customer.getCustomerEmail())) {
                throw new AppException(ErrorCode.EMAIL_EXISTED) ;
            }
            else{
                throw new AppException(ErrorCode.PHONE_EXISTED) ;
            }
        }
    }

    @Override
    public Response updCustomer(Customer customer) {
        Customer customerDB = customerRepository.findById(customer.getCustomerId()).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        );
        String regexEmail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$" ;
        String regexPhone = "^(84|0[3|5|7|8|9])+([0-9]{8})$" ;
        if(!validate(regexEmail, customer.getCustomerEmail()) || !validate(regexPhone,customer.getCustomerPhone())){
            if(!validate( regexEmail, customer.getCustomerEmail())) {
                throw new AppException(ErrorCode.ERR_CUSTOMER_EMAIL_VALID) ;
            }
            else {
                if (!validate(regexPhone, customer.getCustomerPhone())) {
                    throw new AppException(ErrorCode.ERR_CUSTOMER_PHONE_VALID);
                }
            }
        }
        if(customerDB.getCustomerEmail().equals(customer.getCustomerEmail())){
            if(!customerDB.getCustomerPhone().equals(customer.getCustomerPhone())) {
                if (customerRepository.existsByCustomerPhone(customer.getCustomerPhone())) {
                    throw new AppException(ErrorCode.PHONE_EXISTED);
                }
            }
            customerRepository.save(customer);
        }else{
            throw  new AppException(ErrorCode.EMAIL_NO_CHANGE) ;
        }
        return Response.builder()
                .code(200)
                .result(customer)
                .message("Update customer success")
                .build() ;

    }

    @Override
    public Response delCustomer(List<Integer> customerIds) {
        List<Customer> customers = new ArrayList<>() ;
        for(Integer x : customerIds){
            Optional<Customer> customerOptional = customerRepository.findById(x) ;
            if(customerOptional.isPresent()){
                customerOptional.get().setCustomerAvailable(CUSTOMER_AVAILABLE_FALSE);
                Account account = customerOptional.get().getAccount() ;
                account.setAccountAvailable(CUSTOMER_AVAILABLE_FALSE);
                accountRepository.save(account) ;
                customerRepository.save(customerOptional.get()) ;
                customers.add(customerOptional.get()) ;
            }
        }
        return Response.builder()
                .code(200)
                .result(customers)
                .message("Delete customer success")
                .build();
    }

    @Override
    public Response delCustomerNoChange(List<Integer> customerIds) {
        List<Customer> customers = new ArrayList<>() ;
        for(Integer x : customerIds){
            Optional<Customer> customerOptional = customerRepository.findById(x) ;
            if(customerOptional.isPresent()){
                customerRepository.delete(customerOptional.get());
                customers.add(customerOptional.get()) ;
            }
        }
        return Response.builder()
                .code(200)
                .result(customers)
                .message("Delete customer success")
                .build();
    }

    @Override
    public Response sendCode(CustomerRequest request) throws MessagingException, UnsupportedEncodingException {
        String regexEmail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$" ;
        String regexPhone = "^(84|0[3|5|7|8|9])+([0-9]{8})$" ;
        if(validate(regexEmail , request.getCustomerEmail())
           && validate(regexPhone , request.getCustomerPhone())
                &&!customerRepository.existsByCustomerEmailOrCustomerPhone(request.getCustomerEmail() , request.getCustomerPhone())
        ) {
            mailService.sendMail(request.getCustomerEmail()) ;
        }else{
            if(!validate(regexEmail , request.getCustomerEmail())) {
                throw new AppException(ErrorCode.ERR_CUSTOMER_EMAIL_VALID) ;
            }
            else{
                if(!validate(regexPhone , request.getCustomerPhone())) {
                    throw new AppException(ErrorCode.ERR_CUSTOMER_PHONE_VALID);
                }
                else{
                    if(customerRepository.existsByCustomerEmail(request.getCustomerEmail())){
                        throw  new AppException(ErrorCode.EMAIL_EXISTED) ;
                    }
                    else{
                        if(customerRepository.existsByCustomerPhone(request.getCustomerPhone())) {
                            throw new AppException(ErrorCode.PHONE_EXISTED);
                        }
                    }
                }
            }
        }
        return Response.builder()
                .code(200)
                .message("Send email success")
                .result(request)
                .build();
    }

    @Override
    public Response authCode(CustomerRequest request , int code) {
        Response response = mailService.authCode(request.getCustomerEmail() , code) ;
        response.setResult(request);
        return  response ;
    }
}
