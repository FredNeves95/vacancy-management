package com.vacancy.management.vacancymanagement.modules.company.dto;

import lombok.Data;

@Data
public class CreateJobRequestDTO {
  private String name;
  private String description;
  private String level;
}
