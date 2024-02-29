package com.vacancy.management.vacancymanagement.modules.company.useCase;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.vacancy.management.vacancymanagement.exceptions.CompanyNotFoundException;
import com.vacancy.management.vacancymanagement.modules.candidate.dto.AuthCompanyDTO;
import com.vacancy.management.vacancymanagement.modules.company.repository.CompanyRepository;

@Service
public class AuthCompanyUseCase {
  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Value("${security.token.secret}")
  private String secretKey;

  public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
    var company = companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
      () -> {
        throw new CompanyNotFoundException();
      }
    );

      Boolean passwordMatch = passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

      if(!passwordMatch){
        throw new javax.naming.AuthenticationException("Credenciais inválidas");
      }
      Algorithm algorithm = Algorithm.HMAC256(secretKey);
      var token = JWT.create().withIssuer("vacancymanagement")
      .withSubject(company.getId().toString()).sign(algorithm);

      return token;
  }
}