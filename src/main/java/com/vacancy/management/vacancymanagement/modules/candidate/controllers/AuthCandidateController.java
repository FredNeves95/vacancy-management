package com.vacancy.management.vacancymanagement.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.vacancy.management.vacancymanagement.modules.candidate.dto.AuthCandidateRequestDTO;
import com.vacancy.management.vacancymanagement.modules.candidate.useCase.AuthCantidadeUseCase;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthCandidateController {
  @Autowired
  private AuthCantidadeUseCase authCandidateUseCase;

  @PostMapping("/candidate")
  public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) {
    try {
      var authCandidateResponse = authCandidateUseCase.execute(authCandidateRequestDTO);
      return ResponseEntity.ok(authCandidateResponse);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
  }
}
