package com.vacancy.management.vacancymanagement.modules.candidate.useCase;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.vacancy.management.vacancymanagement.modules.candidate.dto.AuthCandidateRequestDTO;
import com.vacancy.management.vacancymanagement.modules.candidate.dto.AuthCandidateResponseDTO;
import com.vacancy.management.vacancymanagement.modules.candidate.repository.CandidateRepository;

@Service
public class AuthCantidadeUseCase {
  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Value("${security.token.secret.candidate}")
  private String secretKey;

  public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException {
    var candidate = this.candidateRepository.findByUsername(authCandidateRequestDTO.username())
    .orElseThrow(() -> {
      throw new UsernameNotFoundException("Invalid credentials");
    });

    var passwordMatches = this.passwordEncoder.matches(authCandidateRequestDTO.password(), candidate.getPassword());
    if(!passwordMatches) {
      throw new AuthenticationException();
    }

    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    var token = JWT.create()
    .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
    .withIssuer("vacancymanagement")
    .withSubject(candidate.getId().toString())
    .withClaim("roles", Arrays.asList("candidate"))
    .sign(algorithm);

    var authCandidateResponse = AuthCandidateResponseDTO.builder().access_token(token).build();
    return authCandidateResponse;
  }
}
