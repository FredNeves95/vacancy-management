package com.vacancy.management.vacancymanagement.modules.candidate.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplyJobResponseDTO {
  private UUID id;
  private UUID candidateId;
  private UUID jobId;
  private LocalDateTime createdAt;
}
