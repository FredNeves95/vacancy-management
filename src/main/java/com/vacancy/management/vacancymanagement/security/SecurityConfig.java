package com.vacancy.management.vacancymanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
  @Autowired
  SecurityFilter securityFilter;

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    http.csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(auth -> {
        auth.requestMatchers("/candidate").permitAll()
        .requestMatchers("/company").permitAll()
        .requestMatchers("/auth/*").permitAll();
        
        auth.anyRequest().authenticated();
      })
      .addFilterBefore(securityFilter, BasicAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
}
