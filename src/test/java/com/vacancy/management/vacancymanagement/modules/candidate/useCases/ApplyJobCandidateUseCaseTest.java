package com.vacancy.management.vacancymanagement.modules.candidate.useCases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.vacancy.management.vacancymanagement.exceptions.JobNotFoundException;
import com.vacancy.management.vacancymanagement.modules.candidate.entities.CandidateEntity;
import com.vacancy.management.vacancymanagement.modules.candidate.repository.CandidateRepository;
import com.vacancy.management.vacancymanagement.modules.candidate.useCase.ApplyJobCandidateUseCase;
import com.vacancy.management.vacancymanagement.modules.company.repository.JobRepository;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

  @InjectMocks
  private ApplyJobCandidateUseCase applyJobCandidateUseCase;

  @Mock
  private CandidateRepository candidateRepository;

  @Mock
  private JobRepository jobRepository;

  @Test
  @DisplayName("Should not be able to apply for a job when candidate is not found")
  public void should_not_be_able_to_apply_for_a_job_with_candidate_not_found(){
    UUID mockedCandidateId = UUID.randomUUID();
    UUID mockedJobId = UUID.randomUUID();
    try {
      applyJobCandidateUseCase.execute(mockedCandidateId, mockedJobId);
    } catch (Exception e) {
      assertThat(e).isInstanceOf(UsernameNotFoundException.class);
      assert e.getMessage().equals("User not found");
    }
  }

  @Test
  @DisplayName("Should not be able to apply for a job when job is not found")
  public void should_not_be_able_to_apply_for_a_job_with_job_not_found(){
   var candidateId = UUID.randomUUID();
   var candidate = new CandidateEntity(); 
   candidate.setId(candidateId);
   when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(candidate));
   try {
    UUID mockedJobId = UUID.randomUUID();
    applyJobCandidateUseCase.execute(candidateId, mockedJobId);
  } catch (Exception e) {
    assertThat(e).isInstanceOf(JobNotFoundException.class);
    assert e.getMessage().equals("Job not found");
  }
  }
}
