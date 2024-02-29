package com.vacancy.management.vacancymanagement.exceptions;

public class CompanyNotFoundException extends RuntimeException {
	public CompanyNotFoundException() {
		super("Empresa não existe");
	}	
}