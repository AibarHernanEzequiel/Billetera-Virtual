package com.aibar.controllers;

import com.aibar.exceptions.*;
import com.aibar.model.Usuario;
import com.aibar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RegisterController {

    private HttpStatus status = HttpStatus.CREATED;

    @Autowired
    private UserService service;

    public RegisterController(UserService service) {
        this.service = service;
    }

    @PostMapping(value = "validar-formulario")
    public ResponseEntity<Map<String, Object>> validarFormularioDeRegistro(@RequestBody RegisterData registerData) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (isValidEmailAndPassword(registerData)) {
                Usuario aRegistrar = service.registrarUsuario(registerData);
                map.put("Email", aRegistrar.getEmail());
                map.put("Nickname", aRegistrar.getNickName());
                map.put("Mensaje", "Te registraste correctamente");
            }
        } catch (InvalidEmailException e) {
            addErrorAndMessageAndSetHttpStatus(map, "P-500", "Ingresaste un email invalido");
        } catch (InvalidLength e) {
            addErrorAndMessageAndSetHttpStatus(map, "P-501", "Ingresaste una clave con longitud invalida, debe contener al menos 8 caracteres");
        } catch (PasswordNotEquals e) {
            addErrorAndMessageAndSetHttpStatus(map, "P-502", "Las claves no coinciden, verifica que sean iguales");
        } catch (NotContainsCapitalLetters e) {
            addErrorAndMessageAndSetHttpStatus(map, "P-503", "La clave debe contener al menos una letra mayuscula");
        } catch (NotContainsNunbers e) {
            addErrorAndMessageAndSetHttpStatus(map, "P-504", "La clave debe contener al menos un numero");
        }
        return new ResponseEntity<>(map, status);
    }

    private boolean isValidEmailAndPassword(RegisterData registerData) throws InvalidEmailException, PasswordNotEquals, NotContainsCapitalLetters, NotContainsNunbers, InvalidLength {
        return isValidEmail(registerData) && isValidPassword(registerData);
    }

    private boolean isValidPassword(RegisterData registerData) throws PasswordNotEquals, NotContainsCapitalLetters, NotContainsNunbers, InvalidLength {
        return PasswordValidator.validatePassword(registerData.getPassword(), registerData.getRepeatPassword());
    }

    private boolean isValidEmail(RegisterData registerData) throws InvalidEmailException {
        return EmailValidator.validateEmail(registerData.getEmail());
    }

    private void addErrorAndMessageAndSetHttpStatus(Map<String, Object> map, String errorCode, String message) {
        map.put("Error", errorCode);
        map.put("Mensaje", message);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
