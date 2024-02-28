package com.vacancy.management.vacancymanagement.modules.company.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity(name = "company")
@Data
public class CompanyEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  
  @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço")
  private String username;

  @Email(message = "O campo [email] deve conter um e-mail válido")
  private String email;

  @Length(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
  @Length(max = 16, message = "A senha deve ter no máximo 16 caracteres")
  private String password;
  
  private String name;
  private String website;
  
  @CreationTimestamp
  private LocalDateTime createdAt;
}
