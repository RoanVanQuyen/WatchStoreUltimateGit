package com.example.watchstoreultimate.repository;

import com.example.watchstoreultimate.entity.Customer;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
@Hidden
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByCustomerEmailOrCustomerPhone(String email , String phone) ;
    boolean existsByCustomerEmail(String email) ;
    boolean existsByCustomerPhone(String phone) ;
    Optional<Customer> findByCustomerIdAndCustomerAvailable(int customerId , boolean Available);
    Page findAllByCustomerAvailable(boolean Available, Pageable pageable) ;
}
