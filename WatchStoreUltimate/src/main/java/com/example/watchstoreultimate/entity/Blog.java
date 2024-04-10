package com.example.watchstoreultimate.entity;

import com.example.watchstoreultimate.constant.ErrorResponseMessage;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Builder@Data
@AllArgsConstructor@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Blog { // ROLE_MANAGER , ADMIN
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int blogId ;
    @Column(columnDefinition = "varchar(200)", nullable = false)
    String blogName ;
    @Column(columnDefinition = "text" , nullable = false)
    String blogContent ;
    @Builder.Default
    int view = 0 ;
    @Builder.Default
    boolean available = true ;
    @Builder.Default
    LocalDate blogCreatDate = LocalDate.now() ;
    @ManyToOne @JoinColumn(name = "customerId")
    Customer customer ;
}
