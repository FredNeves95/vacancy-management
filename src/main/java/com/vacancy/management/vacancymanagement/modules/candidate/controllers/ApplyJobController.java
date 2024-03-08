package com.vacancy.management.vacancymanagement.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.vacancy.management.vacancymanagement.modules.candidate.dto.ApplyJobRequestDTO;
import com.vacancy.management.vacancymanagement.modules.candidate.useCase.ApplyJobCandidateUseCase;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



@RestController
@RequestMapping("/apply")
public class ApplyJobController {
  @Autowired
  private ApplyJobCandidateUseCase applyJobCandidateUseCase;

  @PostMapping("")
  private ResponseEntity<Object> create(HttpServletRequest request, @Valid @RequestBody ApplyJobRequestDTO applyJobRequestDTO) {
    try {
      var candidateId = request.getAttribute("candidate_id").toString();
      var jobId = applyJobRequestDTO.getJobId();  
      var response = applyJobCandidateUseCase.execute(UUID.fromString(candidateId), jobId);
      return ResponseEntity.status(201).body(response);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  } 
}
