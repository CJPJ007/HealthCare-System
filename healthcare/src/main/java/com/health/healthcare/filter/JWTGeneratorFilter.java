package com.health.healthcare.filter;

import java.io.IOException;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.stream.Collectors;
@Component
@Slf4j
public class JWTGeneratorFilter extends OncePerRequestFilter {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.exp.time}")
    private Long jwtExpTime;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("Inside doFilterInternal");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
            String jwt = Jwts.builder().claim("username", authentication.getPrincipal())
            .claim("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
            .signWith(key)
            .issuer("HealthCare")
            .issuedAt(new Date())
            .expiration(new Date((new Date()).getTime()+jwtExpTime))
            .compact();
            response.addHeader("Authorization", jwt);
        } catch (Exception e) {
            log.info("Exception in doFilternalInternal",e);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getServletPath().startsWith("/api/users/getUser");
    }
}
