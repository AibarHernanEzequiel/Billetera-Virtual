package com.aibar.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ModelMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

	private void thenDeberiaEnviarUnMensajeYElStatusCodeDebeSerCreated(ResponseEntity<Map<String, Object>> response) {
		assertThat(response.getBody().get("Mensaje")).isEqualTo("Te registraste correctamente");
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}

	private ResponseEntity<Map<String, Object>> whenEnviaElFormulario(RegisterData data) {
		return controller.validarFormulario(data);
	}

	private RegisterData givenQueUnUsuarioIngresaSusDatosEnElFormularioDeRegistro() {
		return getRegisterData("Ezequiel", "Aibar", "ezequiel@gmail.com", "A1234567", "A1234567");
	}

	private RegisterData getRegisterData(String nombre, String apellido, String email, String clave,
			String repiteClave) {
		return new RegisterData(nombre, apellido, email, clave, repiteClave);
	}

	@Test
	void queUnUsuarioAlIngresarUnEmailInvalidoNoPuedaRegistrarse() {
		RegisterData dataWithInvalidEmailData = givenQueUnUsuarioIngresaUnEmailInvalido();
		ResponseEntity<Map<String, Object>> response = whenEnviaElFormulario(dataWithInvalidEmailData);
		thenElRegistroFallaYDevuelveUnStatus500(response, "P-500", "Ingresaste un email invalido");
	}

	private RegisterData givenQueUnUsuarioIngresaUnEmailInvalido() {
		return getRegisterData("Ezequiel", "Aibar", "ezequiel.com", "1234", "1234");
	}

	private void thenElRegistroFallaYDevuelveUnStatus500(ResponseEntity<Map<String, Object>> response, String error,
			String mensaje) {
		assertThat(response.getBody().get("Error")).isEqualTo(error);
		assertThat(response.getBody().get("Mensaje")).isEqualTo(mensaje);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Test
	void queUnUsuarioCuandoIngreseUnaClaveInvalidaNoPuedaRegistrase() {
		RegisterData dataWithInvalidPassword = givenQueUnUsuarioIngresaUnaClaveInvalida();
		ResponseEntity<Map<String, Object>> response = whenEnviaElFormulario(dataWithInvalidPassword);
		thenElRegistroFallaYDevuelveUnStatus500(response, "P-501", "Ingresaste una clave invalida");
	}

	private RegisterData givenQueUnUsuarioIngresaUnaClaveInvalida() {
		return getRegisterData("Ezequiel", "Aibar", "ezequiel@gmail.com", "A123456", "A123456");
	}
	
	@Test
	void test() {
		
	}
}
