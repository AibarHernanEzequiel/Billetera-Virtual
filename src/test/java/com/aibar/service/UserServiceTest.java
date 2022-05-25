package com.aibar.service;

import com.aibar.controllers.RegisterData;
import com.aibar.exceptions.UsuarioYaExistenteException;
import com.aibar.model.Usuario;
import com.aibar.repositories.UserRepository;
import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService = new UserServiceImpl(userRepository);


    @Test
    void queLanzeUnaExeptionCuandoElUsuarioQueSeQuieraRegistrarseNoPuedaPorqueYaExiste() {
        givenQueExisteUnUsuarioRegistradp();
        ThrowingRunnable throwingRunnable = whenSeRegistra();
        thenLanzaUnaException(throwingRunnable);
    }

    @Test
    void queSePuedaRegistrarUnUsuario() throws UsuarioYaExistenteException {
        givenQueUnUsuarioQuiereRegistrarse();
        Usuario registrado = whenSeRegistraElUsuario();
        thenElUsuarioNoEsNulo(registrado);
    }

    private void thenElUsuarioNoEsNulo(Usuario registrado) {
        verify(userRepository, times(1)).registrarUsuario(any());
        assertThat(registrado).isNotNull();
    }

    private Usuario whenSeRegistraElUsuario() throws UsuarioYaExistenteException {
        Usuario registrado = userService.registrarUsuario(new RegisterData());
        return registrado;
    }

    private void givenQueUnUsuarioQuiereRegistrarse() {
        when(userRepository.registrarUsuario(any())).thenReturn(new Usuario());
    }

    private void givenQueExisteUnUsuarioRegistradp() {
        when(userRepository.buscarUsuarioPorEmail(any())).thenReturn(new Usuario());
    }

    private ThrowingRunnable whenSeRegistra() {
        return () -> {
            userService.registrarUsuario(new RegisterData());
        };
    }

    private void thenLanzaUnaException(ThrowingRunnable throwingRunnable) {
        assertThrows(UsuarioYaExistenteException.class, throwingRunnable);
        verify(userRepository, times(1)).buscarUsuarioPorEmail(any());
    }
}
