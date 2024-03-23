package com.example.watchstoreultimate.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int roleId ;
    @Column(columnDefinition = "varchar(25)" , nullable = false , unique = true)
    String roleName ;
    // map
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
            @JsonIgnore
    List<Account> accounts ;
}
