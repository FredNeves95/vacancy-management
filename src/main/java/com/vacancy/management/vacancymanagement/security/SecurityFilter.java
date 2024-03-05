package com.vacancy.management.vacancymanagement.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vacancy.management.vacancymanagement.providers.JWTProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  @Autowired
  JWTProvider jwtProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
        if(request.getRequestURI().startsWith("/company") || request.getRequestURI().startsWith("/job")){
          SecurityContextHolder.getContext().setAuthentication(null);
          String header = request.getHeader("Authorization");
          
          if(header != null){
            var decodedToken = this.jwtProvider.validateToken(header);
            var subjectToken = decodedToken.getSubject();
            var roles = decodedToken.getClaim("roles").asList(Object.class);
            var grants = roles.stream()
              .map(
                role -> new SimpleGrantedAuthority("ROLE_" + role.toString().toUpperCase())
              ).toList();
            if(subjectToken.isEmpty()){
              response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
              return;
            }
  
            request.setAttribute("company_id", subjectToken);
           
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(subjectToken, null, grants);
  
            SecurityContextHolder.getContext().setAuthentication(auth);
          }
        }

        filterChain.doFilter(request, response);
  }
  
}
