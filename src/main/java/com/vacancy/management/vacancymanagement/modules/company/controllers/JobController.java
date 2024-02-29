package com.vacancy.management.vacancymanagement.modules.company.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.vacancy.management.vacancymanagement.modules.company.entities.JobEntity;
import com.vacancy.management.vacancymanagement.modules.company.useCase.CreateJobUseCase;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/job")
public class JobController {

  @Autowired
  private CreateJobUseCase createJobUseCase;

  @PostMapping("")
  public ResponseEntity<Object> create(@Valid @RequestBody JobEntity jobEntity, HttpServletRequest request) {
    try {
      var companyId = request.getAttribute("company_id");
      jobEntity.setCompanyId(UUID.fromString(companyId.toString()));
      var result = createJobUseCase.execute(jobEntity);
      return ResponseEntity.ok(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }  
}
