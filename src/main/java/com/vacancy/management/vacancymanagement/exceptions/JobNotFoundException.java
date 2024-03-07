package com.vacancy.management.vacancymanagement.exceptions;

public class JobNotFoundException extends RuntimeException {
	public JobNotFoundException() {
		super("Job not found");
	}	
}
