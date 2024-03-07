package com.vacancy.management.vacancymanagement.modules.candidate.useCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vacancy.management.vacancymanagement.exceptions.JobNotFoundException;
import com.vacancy.management.vacancymanagement.modules.candidate.repository.CandidateRepository;
import com.vacancy.management.vacancymanagement.modules.company.repository.JobRepository;

import lombok.NonNull;

@Service
public class ApplyJobCandidateUseCase {
  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private JobRepository jobRepository;

  public void execute(@NonNull UUID candidateId, @NonNull UUID jobId) {
    candidateRepository.findById(candidateId).orElseThrow(() -> {
      throw new UsernameNotFoundException("User not found");
    });

    jobRepository.findById(jobId).orElseThrow(() -> {
      throw new JobNotFoundException();
    });
  }
}
