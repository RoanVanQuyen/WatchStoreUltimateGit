package com.example.watchstoreultimate.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity@Hidden
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int roleId ;
    @Column(columnDefinition = "varchar(25)" , nullable = false , unique = true)
    String roleName ;
    // map
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
            @JsonIgnore
    List<Account> accounts ;
}
