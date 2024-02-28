package com.vacancy.management.vacancymanagement.modules.candidate.controllers;

import com.vacancy.management.vacancymanagement.modules.candidate.entities.CandidateEntity;
import com.vacancy.management.vacancymanagement.modules.candidate.useCase.CreateCandidateUseCase;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/candidate")
public class CandidateController {

  @Autowired
  private CreateCandidateUseCase createCandidateUseCase;

  @PostMapping("")
  public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
    try {
      var result = createCandidateUseCase.execute(candidateEntity);
      return ResponseEntity.ok(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}