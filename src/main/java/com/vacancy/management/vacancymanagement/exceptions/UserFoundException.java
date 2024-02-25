package com.vacancy.management.vacancymanagement.exceptions;

public class UserFoundException extends RuntimeException {
	public UserFoundException() {
		super("Usuário já existe");
	}	
}