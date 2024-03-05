package com.vacancy.management.vacancymanagement.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vacancy.management.vacancymanagement.providers.JWTCandidateProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityCandidateFilter extends OncePerRequestFilter {

  @Autowired
  JWTCandidateProvider jwtCandidateProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
        if(request.getRequestURI().startsWith("/candidate")){
          SecurityContextHolder.getContext().setAuthentication(null);
          String header = request.getHeader("Authorization");
          
          if(header != null){
            var tokenDecoded = this.jwtCandidateProvider.validateToken(header);
            var subjectToken = tokenDecoded.getSubject();
            var roles = tokenDecoded.getClaim("roles").asList(Object.class);
            var grants = roles.stream()
              .map(
                role -> new SimpleGrantedAuthority("ROLE_" + role.toString().toUpperCase())
              ).toList();
            
            if(subjectToken.isEmpty()){
              response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
              return;
            }

            request.setAttribute("candidate_id", subjectToken);          

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(subjectToken, null, grants);

            SecurityContextHolder.getContext().setAuthentication(auth);
        }}

        filterChain.doFilter(request, response);
  }
  
}
