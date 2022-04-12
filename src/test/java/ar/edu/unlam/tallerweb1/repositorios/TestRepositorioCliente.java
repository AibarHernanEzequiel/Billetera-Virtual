package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Cuenta;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class TestRepositorioCliente extends SpringTest {

    @Autowired
    private RepositorioCliente repositorioCliente;

    @Test
    @Rollback
    @Transactional
    public void dadoQueExisteUnClienteRegistradoCuandoBuscoAElClienteEnLaBaseDeDatosPorSuCorreoDeberiaTraermeElCliente() {
        Cliente clienteRegistrado = givenQueExisteUnClienteRegistrado();
        Cliente clienteBuscado = whenBuscoEnLaBaseDedatosUnClienteConElMismoCorreo(clienteRegistrado.getCorreo());
        thenDeberiaObtenerElClienteQueEstoyBuscando(clienteBuscado);
    }

    private void thenDeberiaObtenerElClienteQueEstoyBuscando(Cliente clienteBuscado) {
        assertThat(clienteBuscado).isNotNull();
        assertThat(clienteBuscado.getCorreo()).isEqualTo(clienteBuscado.getCorreo());
    }

    private Cliente givenQueExisteUnClienteRegistrado() {
        return setearLosDatosDelClienteYGuardarlo(new Cliente());
    }

    private Cliente setearLosDatosDelClienteYGuardarlo(Cliente clienteRegistrado) {
        var cuenta = setearCorreoAlClienteYObtenerCuenta(clienteRegistrado);
        guardarClienteYCuenta(clienteRegistrado, cuenta);
        return clienteRegistrado;
    }

    private Cuenta setearCorreoAlClienteYObtenerCuenta(Cliente clienteRegistrado) {
        var cuenta = clienteRegistrado.getCuenta();
        clienteRegistrado.setCorreo("ezequiel@gmail.com");
        return cuenta;
    }

    private void guardarClienteYCuenta(Cliente clienteRegistrado, Cuenta cuenta) {
        session().save(clienteRegistrado);
        session().save(cuenta);
    }

    private Cliente whenBuscoEnLaBaseDedatosUnClienteConElMismoCorreo(String correo) {
        return repositorioCliente.buscarClientePorCorreo(correo);
    }
}
