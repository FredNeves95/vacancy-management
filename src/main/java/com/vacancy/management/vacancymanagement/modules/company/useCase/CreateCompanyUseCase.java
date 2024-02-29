package com.vacancy.management.vacancymanagement.modules.company.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vacancy.management.vacancymanagement.exceptions.CompanyFoundException;
import com.vacancy.management.vacancymanagement.modules.company.dto.CreateCompanyDTO;
import com.vacancy.management.vacancymanagement.modules.company.entities.CompanyEntity;
import com.vacancy.management.vacancymanagement.modules.company.repository.CompanyRepository;

@Service
public class CreateCompanyUseCase {
  @Autowired
  private CompanyRepository companyRepository;
  @Autowired 
  private PasswordEncoder passwordEncoder;

  public CreateCompanyDTO execute(CompanyEntity companyEntity){
     this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail()).ifPresent((company) -> {
      throw new CompanyFoundException();
    });

    var password = this.passwordEncoder.encode(companyEntity.getPassword());
    companyEntity.setPassword(password);
    var company = this.companyRepository.save(companyEntity);
    CreateCompanyDTO createCompanyDTO = new CreateCompanyDTO(company.getId(), company.getName());
    return createCompanyDTO;
  }
}
