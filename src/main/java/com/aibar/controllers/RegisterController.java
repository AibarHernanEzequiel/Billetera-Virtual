package com.aibar.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.message.callback.PasswordValidationCallback;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aibar.exceptions.InvalidEmailException;
import com.aibar.exceptions.InvalidLength;
import com.aibar.exceptions.NotContainsCapitalLetters;
import com.aibar.exceptions.NotContainsNunbers;
import com.aibar.exceptions.PasswordNotEquals;

@RestController
public class RegisterController {

	@PostMapping(value = "validar-formulario")
	public ResponseEntity<Map<String, Object>> validarFormulario(@RequestBody RegisterData registerData) {
		Map<String, Object> map = new HashMap<>();
		HttpStatus status = HttpStatus.CREATED;
		try {
			if (isValidEmail(registerData) && isValidPassword(registerData)) {
				map.put("Mensaje", "Te registraste correctamente");
			}
		} catch (InvalidEmailException e) {
			map.put("Error", "P-500");
			map.put("Mensaje", "Ingresaste un email invalido");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		} catch (PasswordNotEquals e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotContainsCapitalLetters e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotContainsNunbers e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidLength e) {
			map.put("Error", "P-501");
			map.put("Mensaje", "Ingresaste una clave con longitud invalida, debe contener al menos 8 caracteres");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(map, status);
	}

	private boolean isValidPassword(RegisterData registerData)
			throws PasswordNotEquals, NotContainsCapitalLetters, NotContainsNunbers, InvalidLength {
		return PasswordValidator.validatePassword(registerData.getPassword(), registerData.getRepeatPassword());
	}

	private boolean isValidEmail(RegisterData registerData) throws InvalidEmailException {
		return EmailValidator.validateEmail(registerData.getEmail());
	}

}
