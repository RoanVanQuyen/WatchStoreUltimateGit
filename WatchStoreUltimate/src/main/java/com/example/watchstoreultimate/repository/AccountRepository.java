package com.example.watchstoreultimate.repository;

import com.example.watchstoreultimate.entity.Account;
import com.example.watchstoreultimate.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;
@EnableJpaRepositories
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUserNameAndAccountAvailable(String userName , boolean Available) ;
    boolean existsByUserName(String userName) ;
    boolean existsByCustomer(Customer customer) ;
}
