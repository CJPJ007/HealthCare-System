package com.health.healthcare.authprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.health.healthcare.domain.User;
import com.health.healthcare.repos.UserRepository;

import lombok.extern.slf4j.Slf4j;
import java.util.List;
@Component
@Slf4j
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider{

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("Inside authenticate : {} - {}",authentication.getPrincipal(),authentication.getCredentials());
        List<User> user = userRepository.findByEmail(authentication.getName());
        if(user!=null && !user.isEmpty() && user.get(0)!=null){
            if(passwordEncoder.matches(authentication.getCredentials().toString(), user.get(0).getPassword())){
                log.info("User authenticated");
                return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),null ,List.of(new SimpleGrantedAuthority("ADMIN")));
            }else{
                throw new BadCredentialsException("User credentials improper");
            }
        }else{
        throw new UsernameNotFoundException("No user available with : "+authentication.getName());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        log.info("Inside support");
        return authentication.getSimpleName().equalsIgnoreCase(UsernamePasswordAuthenticationToken.class.getSimpleName());
    }
    
}
