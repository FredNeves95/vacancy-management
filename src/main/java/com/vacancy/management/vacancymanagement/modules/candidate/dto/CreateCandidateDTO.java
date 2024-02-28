package com.vacancy.management.vacancymanagement.modules.candidate.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.vacancy.management.vacancymanagement.modules.candidate.entities.CandidateEntity;

import lombok.Getter;

@Getter
public class CreateCandidateDTO {
  private UUID id;
  private String name;
  private String email;
  private String username;
  private LocalDateTime createdAt;
  
  public CreateCandidateDTO(UUID id, String name, String Email, String username, LocalDateTime createdAt ) {
    this.id = id;
    this.name = name;
    this.email = Email;
    this.username = username;
    this.createdAt = createdAt;  
  }
}
