package com.vacancy.management.vacancymanagement.modules.company.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vacancy.management.vacancymanagement.modules.company.entities.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID>{
  Optional<CompanyEntity> findByUsernameOrEmail(String username, String email);
}
