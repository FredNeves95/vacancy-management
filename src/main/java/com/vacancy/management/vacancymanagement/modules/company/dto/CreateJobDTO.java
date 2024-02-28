package com.vacancy.management.vacancymanagement.modules.company.dto;

import java.util.UUID;

import lombok.Getter;

@Getter
public class CreateJobDTO {
  private UUID id;
  private String name;

  public CreateJobDTO(UUID id, String name) {
    this.id = id;
    this.name = name;
  }
}
