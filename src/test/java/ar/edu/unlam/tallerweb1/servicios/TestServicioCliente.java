package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.excepciones.ClienteInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCliente;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TestServicioCliente {

    private ServicioClienteImpl servicioCliente;
    private RepositorioCliente repositorioCliente;

    @Before
    public void init() {
        repositorioCliente = mock(RepositorioCliente.class);
        servicioCliente = new ServicioClienteImpl(repositorioCliente);
    }

    @Test(expected = ClienteInexistenteException.class)
    public void dadoUnCorreoValidoQueNoEstaAsociadoANigunClienteCuandoLoBuscoDeberiaLanzarUnaExcepcion() throws ClienteInexistenteException {
        String correo = givenUnCorreoValidoPeroQueNoEstaAsociadoANingunCliente();
        whenBuscoUnClientePorElCorreoIngresado(correo);
        thenLanzaUnaExcepcion();
    }

    private void whenBuscoUnClientePorElCorreoIngresado(String correo) throws ClienteInexistenteException {
        servicioCliente.buscarClientePorCorreo(correo);
    }

    private String givenUnCorreoValidoPeroQueNoEstaAsociadoANingunCliente() {
        return "lalala@lala.com";
    }

    private void thenLanzaUnaExcepcion() {

    }

    @Test
    public void dadoUnCorreoValidoAsociadoAUnClienteCuandoLosBuscoDeberiaObtenerElCliente() throws ClienteInexistenteException {
        String correo = dadoUnCorreoAsociadoAUnCliente();
        Cliente clienteExitente = whenBuscoElClientePorCorreo(correo);
        thenDeberiaObtenerElClienteAsociadoAlCorreoIngresado(clienteExitente);
        verificarQueSeLlameAlMetodoDelRepositorio();
    }

    private void verificarQueSeLlameAlMetodoDelRepositorio() {
        verify(repositorioCliente, times(1)).buscarClientePorCorreo(any());
    }

    private void thenDeberiaObtenerElClienteAsociadoAlCorreoIngresado(Cliente clienteExitente) {
        assertThat(clienteExitente).isNotNull();
        assertThat(clienteExitente).isInstanceOf(Cliente.class);
    }

    private Cliente whenBuscoElClientePorCorreo(String correo) throws ClienteInexistenteException {
        return servicioCliente.buscarClientePorCorreo(correo);
    }

    private String dadoUnCorreoAsociadoAUnCliente() {
        String correo = "ezequiel@gmail.com";
        Mockito.when(repositorioCliente.buscarClientePorCorreo(correo)).thenReturn(new Cliente());
        return correo;
    }
}
