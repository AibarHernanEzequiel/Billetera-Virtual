package com.aibar.controllers;

import com.aibar.exceptions.InvalidEmailException;

public class EmailValidator {

	public static boolean validateEmail(String email) throws InvalidEmailException {
		if(email.contains("@") && email.endsWith(".com")) return true;
		
		throw new InvalidEmailException();
	}

}
