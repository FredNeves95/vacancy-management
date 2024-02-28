package com.vacancy.management.vacancymanagement.modules.candidate.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vacancy.management.vacancymanagement.exceptions.UserFoundException;
import com.vacancy.management.vacancymanagement.modules.candidate.dto.CreateCandidateDTO;
import com.vacancy.management.vacancymanagement.modules.candidate.entities.CandidateEntity;
import com.vacancy.management.vacancymanagement.modules.candidate.repository.CandidateRepository;

@Service
public class CreateCandidateUseCase {
  
  @Autowired
  private CandidateRepository candidateRepository;

  public CreateCandidateDTO execute(CandidateEntity candidateEntity) {
    this.candidateRepository.findByEmailOrUsername(candidateEntity.getEmail(), candidateEntity.getUsername()).ifPresent((user) -> {
      throw new UserFoundException();
    });
    var candidate = this.candidateRepository.save(candidateEntity);
    CreateCandidateDTO createCandidateDTO = new CreateCandidateDTO(candidate.getId(), candidate.getName(), candidate.getEmail(), candidate.getUsername(), candidate.getCreatedAt());
    return createCandidateDTO;  
  }
}
