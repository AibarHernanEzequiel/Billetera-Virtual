package com.aibar.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RegisterController.class)
public class RegisterControllerTest {

    private RegisterController controller = new RegisterController();

    @Test
    void queCuandoUnUsuarioQuieraRegistrarseValideCorrectamente() {
        RegisterData data = givenQueUnUsuarioIngresaSusDatosEnElFormularioDeRegistro();
        ResponseEntity<Map<String, Object>> response = whenEnviaElFormulario(data);
        thenDeberiaEnviarUnMensajeYElStatusCodeDebeSerCreated(response);
    }

    @Test
    void queUnUsuarioAlIngresarUnEmailInvalidoNoPuedaRegistrarse() {
        RegisterData dataWithInvalidEmailData = givenQueUnUsuarioIngresaUnEmailInvalido();
        ResponseEntity<Map<String, Object>> response = whenEnviaElFormulario(dataWithInvalidEmailData);
        thenElRegistroFallaYDevuelveUnStatus500(response, "P-500", "Ingresaste un email invalido");
    }

    @Test
    void queUnUsuarioCuandoIngreseUnaClaveConLongitudInvalidaNoPuedaRegistrase() {
        RegisterData dataWithInvalidPassword = givenQueUnUsuarioIngresaUnaClaveInvalida();
        ResponseEntity<Map<String, Object>> response = whenEnviaElFormulario(dataWithInvalidPassword);
        thenElRegistroFallaYDevuelveUnStatus500(response, "P-501", "Ingresaste una clave con longitud invalida, debe contener al menos 8 caracteres");
    }

    @Test
    void queUnUsuarioCuandoIngreseClavesDistintasNoPuedaRegistrarse() {
        RegisterData passwordsNotEquals = givenQueUnUsuarioIngresaClavesDistintas();
        ResponseEntity<Map<String, Object>> response = whenEnviaElFormulario(passwordsNotEquals);
        thenElRegistroFallaYDevuelveUnStatus500(response, "P-502", "Las claves no coinciden, verifica que sean iguales");
    }

    @Test
    public void queUnUsuarioCuandoIngreseUnaClaveSinMayusculasNoPuedaRegistrase() {
        RegisterData passwordWithoutCapitalsLetter = givenQueUnUsuarioIngresaUnaClaveSinMayusculas();
        ResponseEntity<Map<String, Object>> response = whenEnviaElFormulario(passwordWithoutCapitalsLetter);
        thenElRegistroFallaYDevuelveUnStatus500(response, "P-503", "La clave debe contener al menos una letra mayuscula");
    }

    private RegisterData givenQueUnUsuarioIngresaSusDatosEnElFormularioDeRegistro() {
        return getRegisterData("Ezequiel", "Aibar", "ezequiel@gmail.com", "A1234567", "A1234567");
    }

    private RegisterData givenQueUnUsuarioIngresaUnEmailInvalido() {
        return getRegisterData("Ezequiel", "Aibar", "ezequiel.com", "1234", "1234");
    }

    private RegisterData givenQueUnUsuarioIngresaUnaClaveInvalida() {
        return getRegisterData("Ezequiel", "Aibar", "ezequiel@gmail.com", "A123456", "A123456");
    }

    private RegisterData givenQueUnUsuarioIngresaClavesDistintas() {
        return getRegisterData("Ezequiel", "Aibar", "ezequiel@gmail.com", "A1234567", "A1234568");
    }

    private RegisterData givenQueUnUsuarioIngresaUnaClaveSinMayusculas() {
        return getRegisterData("Ezequiel", "Aibar", "ezequiel@gmail.com", "a1234567", "a1234567");
    }

    private ResponseEntity<Map<String, Object>> whenEnviaElFormulario(RegisterData data) {
        return controller.validarFormulario(data, new HashMap<String, Object>());
    }

    private void thenDeberiaEnviarUnMensajeYElStatusCodeDebeSerCreated(ResponseEntity<Map<String, Object>> response) {
        assertThat(response.getBody().get("Mensaje")).isEqualTo("Te registraste correctamente");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    private void thenElRegistroFallaYDevuelveUnStatus500(ResponseEntity<Map<String, Object>> response, String error, String mensaje) {
        assertThat(response.getBody().get("Error")).isEqualTo(error);
        assertThat(response.getBody().get("Mensaje")).isEqualTo(mensaje);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private RegisterData getRegisterData(String nombre, String apellido, String email, String clave, String repiteClave) {
        return new RegisterData(nombre, apellido, email, clave, repiteClave);
    }
}
