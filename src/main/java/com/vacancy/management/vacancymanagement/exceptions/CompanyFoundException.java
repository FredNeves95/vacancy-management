package com.vacancy.management.vacancymanagement.exceptions;

public class CompanyFoundException extends RuntimeException {
	public CompanyFoundException() {
		super("Empresa já existe");
	}	
}