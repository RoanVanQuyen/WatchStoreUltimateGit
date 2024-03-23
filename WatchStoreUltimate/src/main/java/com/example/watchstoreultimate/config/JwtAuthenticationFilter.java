package com.example.watchstoreultimate.config;

import com.example.watchstoreultimate.entity.Account;
import com.example.watchstoreultimate.repository.AccountRepository;
import com.example.watchstoreultimate.service.AccountService;
import com.example.watchstoreultimate.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    JwtService jwtService ;
    @Autowired
    AccountService accountService ;
    @Autowired
    AccountRepository accountRepository ;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION) ;
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request ,response);
            return;
        }
        authHeader = authHeader.substring(7);
        String userName = jwtService.extractUsername(authHeader);
        UserDetails userDetails = accountService.loadUserByUsername(userName) ;
        Account account = accountRepository.findByUserNameAndAccountAvailable(userName , true).orElseThrow(
                () -> new UsernameNotFoundException("Sai tên đăng nhập")
        ) ;
        if(jwtService.isTokenValid(authHeader , account)){
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext() ;
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken( account, null , account.getAuthorities()) ;
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            securityContext.setAuthentication(authToken);
            SecurityContextHolder.setContext(securityContext);
        }
        filterChain.doFilter(request,response);
    }
}
