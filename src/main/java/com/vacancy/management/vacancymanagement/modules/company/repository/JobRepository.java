package com.vacancy.management.vacancymanagement.modules.company.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vacancy.management.vacancymanagement.modules.company.entities.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
}
