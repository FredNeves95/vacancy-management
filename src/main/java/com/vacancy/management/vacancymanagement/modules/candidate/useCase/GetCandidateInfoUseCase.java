package com.vacancy.management.vacancymanagement.modules.candidate.useCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vacancy.management.vacancymanagement.modules.candidate.dto.GetCandidateInfoDTO;
import com.vacancy.management.vacancymanagement.modules.candidate.repository.CandidateRepository;

@Service
public class GetCandidateInfoUseCase {
  @Autowired
  private CandidateRepository candidateRepository;

  public GetCandidateInfoDTO execute(String candidateId) {
    var candidate = candidateRepository.findById(UUID.fromString(candidateId)).orElseThrow(() -> {
      throw new UsernameNotFoundException("User not found");
    });

    var candidateInfoDTO = 
      GetCandidateInfoDTO.builder()
      .id(candidate.getId())
      .name(candidate.getName())
      .username(candidate.getUsername())
      .email(candidate.getEmail())
      .description(candidate.getDescription())
      .curriculum(candidate.getCurriculum())
      .createdAt(candidate.getCreatedAt())
      .build();

    return candidateInfoDTO;
  }
}
