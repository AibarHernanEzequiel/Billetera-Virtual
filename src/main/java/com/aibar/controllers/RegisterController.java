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
            status = getHttpStatus(map, "P-500", "Ingresaste un email invalido");

        } catch (PasswordNotEquals e) {
            status = getHttpStatus(map, "P-502", "Las claves ingresadas no coinciden");

        } catch (NotContainsCapitalLetters e) {
            status = getHttpStatus(map, "P-503", "La clave debe contener una mayuscula");

        } catch (NotContainsNunbers e) {
            status = getHttpStatus(map, "P-504", "La clave debe contener al menos un numero");

        } catch (InvalidLength e) {
            status = getHttpStatus(map, "P-501", "Ingresaste una clave con longitud invalida, debe contener al menos 8 caracteres");
        }

        return new ResponseEntity<>(map, status);
    }

    private boolean isValidPassword(RegisterData registerData) throws PasswordNotEquals, NotContainsCapitalLetters, NotContainsNunbers, InvalidLength {
        return PasswordValidator.validatePassword(registerData.getPassword(), registerData.getRepeatPassword());
    }

    private boolean isValidEmail(RegisterData registerData) throws InvalidEmailException {
        return EmailValidator.validateEmail(registerData.getEmail());
    }

    private HttpStatus getHttpStatus(Map<String, Object> map, String codeError, String mensaje) {
        map.put("Error", codeError);
        map.put("Mensaje", mensaje);
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
