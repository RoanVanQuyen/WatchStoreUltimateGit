package com.example.watchstoreultimate.repository;

import com.example.watchstoreultimate.entity.Account;
import com.example.watchstoreultimate.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token,  Integer> {
    Optional<Token> findByRefreshToken(String refreshToken) ;
    @Query("SELECT COUNT (*) FROM " +
            "Token t " +
            "WHERE t.expirationToken >= current time " +
            "AND t.account = :account")
    int countByAccount(Account account) ;
    List<Token> findTokenByAccount(Account account) ;
    boolean existsByTokenContentAndAccount(String token , Account account) ;
    Optional<Token> findTokenByTokenContent(String token) ;
}
