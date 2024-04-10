package com.example.watchstoreultimate.repository;

import com.example.watchstoreultimate.entity.Account;
import com.example.watchstoreultimate.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token,  Integer> {
    Optional<Token> findByRefreshToken(String refreshToken) ;
    int countByAccount(Account account) ;
    List<Token> findTokenByAccount(Account account) ;
    boolean existsByTokenContentAndAccount(String token , Account account) ;
}
