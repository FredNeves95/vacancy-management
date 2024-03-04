package com.vacancy.management.vacancymanagement.modules.company.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.vacancy.management.vacancymanagement.modules.company.dto.AuthCompanyDTO;
import com.vacancy.management.vacancymanagement.modules.company.useCase.AuthCompanyUseCase;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/auth")
public class AuthCompanyController {
    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;
  
    @PostMapping("/company")
    public ResponseEntity<Object> signIn(@RequestBody AuthCompanyDTO authCompanyDTO) {
      try {
        var token = authCompanyUseCase.execute(authCompanyDTO);
        return ResponseEntity.ok(token);
      } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
      }

    }
    
}
