package com.vacancy.management.vacancymanagement.modules.company.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vacancy.management.vacancymanagement.modules.company.dto.CreateJobDTO;
import com.vacancy.management.vacancymanagement.modules.company.entities.JobEntity;
import com.vacancy.management.vacancymanagement.modules.company.repository.JobRepository;

@Service
public class CreateJobUseCase {
  @Autowired
  private JobRepository jobRepository;
  public CreateJobDTO execute(JobEntity jobEntity){
    var result = jobRepository.save(jobEntity);
    return new CreateJobDTO(result.getId(), result.getName());
  }
}
