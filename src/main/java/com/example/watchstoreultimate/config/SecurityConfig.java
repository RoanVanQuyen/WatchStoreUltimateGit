package com.example.watchstoreultimate.config;

import com.example.watchstoreultimate.constant.RoleConstant;
import com.example.watchstoreultimate.constant.UrlConstant;
import com.example.watchstoreultimate.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    AccountService accountService ;
    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter ;

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider() ;
        daoAuthenticationProvider.setUserDetailsService(accountService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return  daoAuthenticationProvider ;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration x) throws Exception {
        return x.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder() ;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf( csrf -> csrf.disable())
                .authorizeHttpRequests(req -> {
                    req.requestMatchers(
                            "/account" + UrlConstant.AccountURL.SIGN_IN
                            ,"/account" + UrlConstant.AccountURL.REGISTER
                            , UrlConstant.AccountURL.FORGET_PASSWORD
                            , "swagger-ui/index.html" +"/**"
                            , "/v3/api-docs/**"
                            , "/swagger-ui/**"
//                            , "/**"
                            , UrlConstant.AccountURL.PRE_FIX + UrlConstant.FIND + "/**"
                            , UrlConstant.AddressURL.PRE_FIX + UrlConstant.FIND + "/**"
                            , UrlConstant.BrandURL.PRE_FIX + UrlConstant.FIND + "/**"
                            , UrlConstant.CartURL.PRE_FIX + UrlConstant.FIND + "/**"
                            , UrlConstant.CategoryURL.PRE_FIX + UrlConstant.FIND + "/**"
                            , UrlConstant.CommentURL.PRE_FIX + UrlConstant.FIND + "/**"
                            , UrlConstant.CustomerURL.PRE_FIX + UrlConstant.FIND + "/**"
                            , UrlConstant.ProductURL.PRE_FIX + UrlConstant.FIND + "/**"
                            , UrlConstant.ProductDetailsURL.PRE_FIX + UrlConstant.FIND + "/**"
                            , UrlConstant.ProductImageURL.PRE_FIX + UrlConstant.FIND + "/**"
                            , UrlConstant.BlogUrl.PRE_FIX + UrlConstant.FIND + "/**"
                    ).permitAll() ;


                    req.requestMatchers(
                            UrlConstant.AccountURL.PRE_FIX + UrlConstant.ROLE_USER + "/**"
                            , UrlConstant.AddressURL.PRE_FIX + UrlConstant.ROLE_USER + "/**"
                            , UrlConstant.BrandURL.PRE_FIX + UrlConstant.ROLE_USER + "/**"
                            , UrlConstant.CartURL.PRE_FIX + UrlConstant.ROLE_USER + "/**"
                            , UrlConstant.CategoryURL.PRE_FIX + UrlConstant.ROLE_USER + "/**"
                            , UrlConstant.CommentURL.PRE_FIX + UrlConstant.ROLE_USER + "/**"
                            , UrlConstant.CustomerURL.PRE_FIX + UrlConstant.ROLE_USER + "/**"
                            , UrlConstant.ProductURL.PRE_FIX + UrlConstant.ROLE_USER + "/**"
                            , UrlConstant.ProductDetailsURL.PRE_FIX + UrlConstant.ROLE_USER + "/**"
                            , UrlConstant.ProductImageURL.PRE_FIX + UrlConstant.ROLE_USER + "/**"
//                            , UrlConstant.PurchaseUrl.PRE_FIX + UrlConstant.ROLE_MANAGER + "/**"
                    ).hasAnyAuthority(RoleConstant.USER , RoleConstant.MANAGER, RoleConstant.ADMIN ) ;


                    req.requestMatchers(
                            UrlConstant.AccountURL.PRE_FIX + UrlConstant.ROLE_MANAGER + "/**"
                            , UrlConstant.AddressURL.PRE_FIX + UrlConstant.ROLE_MANAGER + "/**"
                            , UrlConstant.BrandURL.PRE_FIX + UrlConstant.ROLE_MANAGER + "/**"
                            , UrlConstant.CartURL.PRE_FIX + UrlConstant.ROLE_MANAGER + "/**"
                            , UrlConstant.CategoryURL.PRE_FIX + UrlConstant.ROLE_MANAGER + "/**"
                            , UrlConstant.CommentURL.PRE_FIX + UrlConstant.ROLE_MANAGER + "/**"
                            , UrlConstant.CustomerURL.PRE_FIX + UrlConstant.ROLE_MANAGER + "/**"
                            , UrlConstant.ProductURL.PRE_FIX + UrlConstant.ROLE_MANAGER + "/**"
                            , UrlConstant.ProductDetailsURL.PRE_FIX + UrlConstant.ROLE_MANAGER + "/**"
                            , UrlConstant.ProductImageURL.PRE_FIX + UrlConstant.ROLE_MANAGER + "/**"
                            , UrlConstant.BlogUrl.PRE_FIX + UrlConstant.ROLE_MANAGER + "/**"
                    ).hasAnyAuthority(RoleConstant.MANAGER, RoleConstant.ADMIN) ;
                    req.requestMatchers(
                            UrlConstant.CustomerURL.PRE_FIX + UrlConstant.ROLE_ADMIN + "/**"// can bo sung
                    ).hasAnyAuthority(RoleConstant.ADMIN) ;
                    req.anyRequest().permitAll() ;
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter , UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
