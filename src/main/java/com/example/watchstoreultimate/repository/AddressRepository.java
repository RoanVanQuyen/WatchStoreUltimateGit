package com.example.watchstoreultimate.repository;

import com.example.watchstoreultimate.entity.Address;
import com.example.watchstoreultimate.entity.Customer;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
@Hidden
public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Query(value = "FROM Address a " +
            "WHERE a.customer.customerId = :customerId")
    Optional<Address> findByCustomer(int customerId) ;
    boolean existsByCustomer(Customer customer) ;
}
