package com.vacancy.management.vacancymanagement.modules.candidate.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCandidateInfoDTO {
  private UUID id;
  private String name;
  private String email;
  private String username;
  private String description;
  private String curriculum;
  private LocalDateTime createdAt;
}
