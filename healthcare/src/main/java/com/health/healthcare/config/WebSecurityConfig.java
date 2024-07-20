package com.health.healthcare.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.health.healthcare.filter.JWTGeneratorFilter;
import com.health.healthcare.filter.JWTValidatorFilter;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig {
    
    @Autowired
    JWTGeneratorFilter jwtGeneratorFilter;

    @Autowired
    JWTValidatorFilter jwtValidatorFilter;

    @Bean
    SecurityFilterChain configureSecurityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
        .sessionManagement(smCustomizer->smCustomizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .cors((corsCustomizer)->{
            corsCustomizer.configurationSource(new CorsConfigurationSource() {
                @Override
                public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
                    corsConfiguration.setAllowedHeaders(List.of("*"));
                    corsConfiguration.setAllowCredentials(true);
                    corsConfiguration.setAllowedMethods(List.of("*"));
                    corsConfiguration.setMaxAge(3600L);
                    corsConfiguration.setExposedHeaders(List.of("Authorization"));
                    return corsConfiguration;
                }
            });
        }).csrf((csrfCustomizer)->csrfCustomizer.disable())
        .authorizeHttpRequests((authorizeHttpRequestsCustomizer)->
        {
            authorizeHttpRequestsCustomizer
                        .requestMatchers("/api/users/createUser").permitAll()
                        .anyRequest().authenticated()
                        ;
        })
        .addFilterAfter(jwtGeneratorFilter, BasicAuthenticationFilter.class)
        .addFilterBefore(jwtValidatorFilter, BasicAuthenticationFilter.class)
        .httpBasic(Customizer.withDefaults())
        .formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder bcyrpyPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
