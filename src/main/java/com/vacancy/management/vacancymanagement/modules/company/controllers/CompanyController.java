package com.vacancy.management.vacancymanagement.modules.company.controllers;

import com.vacancy.management.vacancymanagement.modules.company.entities.CompanyEntity;
import com.vacancy.management.vacancymanagement.modules.company.useCase.CreateCompanyUseCase;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/company")
public class CompanyController {

  @Autowired
  private CreateCompanyUseCase createCompanyUseCase;

  @PostMapping("")
  public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity) {
    try {
      var result = createCompanyUseCase.execute(companyEntity);
      return ResponseEntity.ok(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
