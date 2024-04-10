package com.example.watchstoreultimate.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data @AllArgsConstructor@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Token {
    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    int tokenId  ;
    @Column
    String tokenContent ;
    @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime expirationToken ;
    @Column
    String refreshToken ;
    @Column
    LocalDateTime expirationRefreshToken ;
    @ManyToOne @JoinColumn(name = "accountId")
    Account account ;
}
