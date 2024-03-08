package com.vacancy.management.vacancymanagement.modules.candidate.useCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vacancy.management.vacancymanagement.exceptions.JobNotFoundException;
import com.vacancy.management.vacancymanagement.modules.candidate.dto.ApplyJobResponseDTO;
import com.vacancy.management.vacancymanagement.modules.candidate.entities.ApplyJobEntity;
import com.vacancy.management.vacancymanagement.modules.candidate.repository.ApplyJobRepository;
import com.vacancy.management.vacancymanagement.modules.candidate.repository.CandidateRepository;
import com.vacancy.management.vacancymanagement.modules.company.repository.JobRepository;

import lombok.NonNull;

@Service
public class ApplyJobCandidateUseCase {
  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private JobRepository jobRepository;

  @Autowired
  private ApplyJobRepository applyJobRepository;

  public ApplyJobResponseDTO execute(@NonNull UUID candidateId, @NonNull UUID jobId) {
    candidateRepository.findById(candidateId).orElseThrow(() -> {
      throw new UsernameNotFoundException("User not found");
    });

    jobRepository.findById(jobId).orElseThrow(() -> {
      throw new JobNotFoundException();
    });
    
    var applyJob = ApplyJobEntity.builder().candidateId(candidateId).jobId(jobId).build();
    applyJobRepository.save(applyJob);

    var applyJobResponseDTO = new ApplyJobResponseDTO(applyJob.getId(), applyJob.getCandidateId(), applyJob.getJobId(), applyJob.getCreatedAt());
    return applyJobResponseDTO;
  }
}
