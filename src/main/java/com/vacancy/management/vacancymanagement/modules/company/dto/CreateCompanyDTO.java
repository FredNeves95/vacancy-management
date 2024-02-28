package com.vacancy.management.vacancymanagement.modules.company.dto;

import java.util.UUID;

import lombok.Getter;

@Getter
public class CreateCompanyDTO {
  private UUID id;
  private String name;

  public CreateCompanyDTO(UUID id, String name) {
    this.id = id;
    this.name = name;
  }
}
