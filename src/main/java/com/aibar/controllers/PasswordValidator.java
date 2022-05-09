package com.aibar.controllers;

import java.util.Iterator;

import com.aibar.exceptions.InvalidLength;
import com.aibar.exceptions.NotContainsCapitalLetters;
import com.aibar.exceptions.NotContainsNunbers;
import com.aibar.exceptions.PasswordNotEquals;

public class PasswordValidator {

	public static boolean validatePassword(String password, String repeatPassword)
			throws PasswordNotEquals, NotContainsCapitalLetters, NotContainsNunbers, InvalidLength {
		boolean valida = false;
			
		if (isEqueals(password, repeatPassword)) {
			valida = true;
		}
		if (isValidateLength(password)) {
			valida = true;
		}
		if (containsCapitalLetters(password)) {
			valida = true;
		}
		if (containsNumbers(password)) {
			valida = true;
		}
		return valida;
	}

	private static boolean isValidateLength(String password) throws InvalidLength {
		if(password.length() == 8) return true;
		throw new InvalidLength();
	}

	private static boolean containsNumbers(String password) throws NotContainsNunbers {
		for (int i = 0; i < password.length(); i++) {
			if (Character.isDigit(password.charAt(i)))
				return true;
		}
		throw new NotContainsNunbers();
	}

	private static boolean containsCapitalLetters(String password) throws NotContainsCapitalLetters {
		for (int i = 0; i < password.length(); i++) {
			if (Character.isUpperCase(password.charAt(i))) {
				return true;
			}
		}
		throw new NotContainsCapitalLetters();
	}

	private static boolean isEqueals(String password, String repeatPassword) throws PasswordNotEquals {
		if (password.equals(repeatPassword)) {
			return true;
		}
		throw new PasswordNotEquals();
	}

}
