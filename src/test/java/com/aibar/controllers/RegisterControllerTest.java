package com.aibar.controllers;

import com.aibar.model.Usuario;
import com.aibar.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RegisterControllerTest {

    @InjectMocks
    private RegisterController controller;
    @Mock
    private UserService service;
    private final HashMap<String, Object> map = new HashMap<>();
    private String nickname = "EzequielAibar";

    @Test
    void queCuandoUnUsuarioQuieraRegistrarseValideCorrectamente() {
        RegisterData data = givenQueUnUsuarioIngresaSusDatosEnElFormularioDeRegistroCorrectamente();
        when(service.registrarUsuario(data)).thenReturn(new Usuario(data));
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

    @Test
    public void test() {
        RegisterData registerData = obtenerDatosDeRegistroDeElUsuarioARegistrar();
        ResponseEntity<Map<String, Object>> response = whenEnviaElFormulario(registerData);
        thenDeberiaEnviarUnMensajeYElStatusCodeDebeSerCreated(response);
        AndThenDeberiaVerificarQueSellamaAlMetodoDelServicioQueRegistraAlUsuario(response, registerData);
    }

    private RegisterData obtenerDatosDeRegistroDeElUsuarioARegistrar() {
        RegisterData registerData = givenQueUnUsuarioIngresaSusDatosEnElFormularioDeRegistroCorrectamente();
        Usuario usuario = new Usuario(registerData);
        when(service.registrarUsuario(registerData)).thenReturn(usuario);
        return registerData;
    }

    private void AndThenDeberiaVerificarQueSellamaAlMetodoDelServicioQueRegistraAlUsuario(ResponseEntity<Map<String, Object>> response, RegisterData registerData) {
        verify(service, times(1)).registrarUsuario(any());
        assertThat(response.getBody().get("Email")).isEqualTo(registerData.getEmail());
        assertThat(response.getBody().get("Nickname")).isEqualTo(registerData.getNickName());
    }

    private RegisterData givenQueUnUsuarioIngresaSusDatosEnElFormularioDeRegistroCorrectamente() {
        return getRegisterData("Ezequiel", "Aibar", "ezequiel@gmail.com", "A1234567", "A1234567", nickname);
    }

    private RegisterData givenQueUnUsuarioIngresaUnEmailInvalido() {
        return getRegisterData("Ezequiel", "Aibar", "ezequiel.com", "1234", "1234", nickname);
    }

    private RegisterData givenQueUnUsuarioIngresaUnaClaveInvalida() {
        return getRegisterData("Ezequiel", "Aibar", "ezequiel@gmail.com", "A123456", "A123456", nickname);
    }

    private RegisterData givenQueUnUsuarioIngresaClavesDistintas() {
        return getRegisterData("Ezequiel", "Aibar", "ezequiel@gmail.com", "A1234567", "A1234568", nickname);
    }

    private RegisterData givenQueUnUsuarioIngresaUnaClaveSinMayusculas() {
        return getRegisterData("Ezequiel", "Aibar", "ezequiel@gmail.com", "a1234567", "a1234567", nickname);
    }

    private RegisterData givenQueUnUsuarioIngresaUnaClaveSinNumeros() {
        return getRegisterData("Ezequiel", "Aibar", "ezequiel@gmail.com", "Qwertyui", "Qwertyui", nickname);
    }

    private ResponseEntity<Map<String, Object>> whenEnviaElFormulario(RegisterData data) {
        return controller.validarFormularioDeRegistro(data);
    }

    private void thenDeberiaEnviarUnMensajeYElStatusCodeDebeSerCreated(ResponseEntity<Map<String, Object>> response) {
        assertThat(Objects.requireNonNull(response.getBody()).get("Mensaje")).isEqualTo("Te registraste correctamente");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    private void thenElRegistroFallaYDevuelveUnStatus500(ResponseEntity<Map<String, Object>> response, String error, String mensaje) {
        assertThat(Objects.requireNonNull(response.getBody()).get("Error")).isEqualTo(error);
        assertThat(response.getBody().get("Mensaje")).isEqualTo(mensaje);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private RegisterData getRegisterData(String nombre, String apellido, String email, String clave, String repiteClave, String nickname) {
        return new RegisterData(nickname, nombre, apellido, email, clave, repiteClave);
    }
}
