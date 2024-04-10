package com.example.watchstoreultimate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@Builder@Hidden
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int commentId;
    @Column(columnDefinition = "text")
    String commentContent ;
    @Builder.Default
    @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime commentDate = LocalDateTime.now();
    @ManyToOne @JoinColumn(name = "customer_id")
    Customer customer ;
    @ManyToOne @JoinColumn(name ="product_id")
    Product product ;
    @ManyToOne @JoinColumn(name = "commentParentId")
    Comment commentParent ;
    @Builder.Default
    boolean commentAvailable = true;

    @OneToMany(mappedBy = "commentParent")
            @JsonIgnore
    List<Comment> comments = new ArrayList<>() ;

}
