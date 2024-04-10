package com.example.watchstoreultimate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity @Hidden
@Data @Builder
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int accountId ;
    @Column(columnDefinition = "varchar(60) collate 'utf8_bin'" , unique = true ,nullable = false)
            @Size(min = 5 , message = "Tên đăng nhập chứa ít nhất 5 kí tự")
    String userName ;
    @Column(columnDefinition = "varchar(60)  collate 'utf8_bin'"  ,nullable = false)
            @Size(min = 8 , message = "Mật khẩu cần chứa ít nhất 8 kí tự ")
    String password ;
    @ManyToOne @JoinColumn(name = "roleId")
    Role role ;
    @Builder.Default
    boolean accountAvailable = true ;
    @OneToOne @JoinColumn(name = "customerId")
    Customer customer ;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getRoleName()));
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true ;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
