package com.aibar.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class RegisterControllerTest {

    private final RegisterController controller = new RegisterController();

    @Test
    void queCuandoUnUsuarioQuieraRegistrarseValideCorrectamente() {
        RegisterData data = givenQueUnUsuarioIngresaSusDatosEnElFormularioDeRegistroCorrectamente();
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

    @Test
    public void queUnUsuarioCuandoIngreseUnaClaveSinNumerosNoPuedaRegistrarse() {
        RegisterData passwordWithOutNumbers = givenQueUnUsuarioIngresaUnaClaveSinNumeros();
        ResponseEntity<Map<String, Object>> response = whenEnviaElFormulario(passwordWithOutNumbers);
        thenElRegistroFallaYDevuelveUnStatus500(response, "P-504", "La clave debe contener al menos un numero");
    }

    private RegisterData givenQueUnUsuarioIngresaSusDatosEnElFormularioDeRegistroCorrectamente() {
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

    private RegisterData givenQueUnUsuarioIngresaUnaClaveSinNumeros() {
        return getRegisterData("Ezequiel", "Aibar", "ezequiel@gmail.com", "Qwertyui", "Qwertyui");
    }

    private ResponseEntity<Map<String, Object>> whenEnviaElFormulario(RegisterData data) {
        return controller.validarFormularioDeRegistro(data, new HashMap<String, Object>());
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
