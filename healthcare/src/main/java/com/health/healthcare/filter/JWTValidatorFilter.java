package com.health.healthcare.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JWTValidatorFilter extends OncePerRequestFilter {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.exp.time}")
    private Long jwtExpTime;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("Inside doFilterInternal");
        try {
            // Implement JWT token validation logic here
            String token = request.getHeader("Authorization");
            log.info("token : {}", token);
            if (token == null || !isValidToken(token)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            } else {
                SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

                Claims claims = (Claims) Jwts.parser().verifyWith(key)
                        .build().parse(token).getPayload();

                Authentication authentication = new UsernamePasswordAuthenticationToken(claims.get("username"), null,
                        Arrays.asList(claims.get("authorities").toString().split(",")).stream()
                                .map((authority) -> new SimpleGrantedAuthority(authority))
                                .collect(Collectors.toList()));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            log.error("Error validating JWT token", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().startsWith("/api/users/");
    }

    public boolean isValidToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

            Claims claims = (Claims) Jwts.parser().verifyWith(key)
                    .build().parse(token).getPayload();

            // Check if the token has expired
            Date expirationDate = claims.getExpiration();
            if (expirationDate.before(new Date())) {
                return false;
            }
            return true;
        } catch (JwtException e) {
            log.error("Exception in validateToken", e);
            return false;
        }
    }
}
