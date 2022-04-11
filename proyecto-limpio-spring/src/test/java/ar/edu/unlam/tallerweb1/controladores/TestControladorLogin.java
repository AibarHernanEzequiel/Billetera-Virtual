package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.excepciones.ClaveInvalidaException;
import ar.edu.unlam.tallerweb1.excepciones.CorreoInvalidoException;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TestControladorLogin {

    private ControladorLogin controladorLogin;
    private ModelAndView modelAndView;
    private ServicioLogin servicioLogin;

    @Before
    public void init() {
        servicioLogin = mock(ServicioLogin.class);
        controladorLogin = new ControladorLogin(servicioLogin);
        modelAndView = new ModelAndView();
    }

    @Test
    public void queSeMuestreLaHomePageAlInicioConUnFormularioDeLogin() {
        givenQueExisteUncliente();
        whenIngresaALaHomePage();
        thenDeberiaMostrarseLaHomePageConUnFormularioDeLogin(modelAndView);
    }

    private void givenQueExisteUncliente() {
    }

    private void whenIngresaALaHomePage() {
        this.modelAndView = controladorLogin.irALaHomePage();
    }

    private void thenDeberiaMostrarseLaHomePageConUnFormularioDeLogin(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("home");
        assertThat(modelAndView.getModelMap().get("datosLogin")).isNotNull();
        assertThat(modelAndView.getModelMap().get("datosLogin")).isInstanceOf(DatosLogin.class);
    }

    @Test
    public void dadoQueUnClienteCompletaElFormDeLoginCuandoLoEnviaDeberiaAccederCorrectamente() throws ClaveInvalidaException, CorreoInvalidoException {
        var datosLogin = givenQueExitenUnClienteRegistrado();
        whenEnviaAValidarElFormularioConLosDatosIngresados(datosLogin);
        thenDeberiaValidarCorrectamenteYRedireccionarALaPaginaPrincipalConUnMensajeDeExito(datosLogin);
    }


    private DatosLogin givenQueExitenUnClienteRegistrado() throws ClaveInvalidaException {
        return setearDatosDeLogin("lala@lala.com", "Aa####04");
    }

    private void thenDeberiaValidarCorrectamenteYRedireccionarALaPaginaPrincipalConUnMensajeDeExito(DatosLogin datosLogin) {
        assertThat(datosLogin.getValidadorDeCorreo()).isTrue();
        assertThat(datosLogin.getValidadorDeCorreo()).isTrue();
        assertThat(modelAndView.getModelMap().get("registro_exitoso")).isNotNull();
        assertThat(modelAndView.getModelMap().get("registro_exitoso")).isEqualTo("true");
        assertThat(modelAndView.getViewName()).isEqualTo("redirect:/principal");
        verify(servicioLogin, times(1)).buscarClientePorCorreo(any());
    }

    @Test
    public void dadoQueUnclienteRegistradoIngresaUnCorreoInvalidoCuandoEnviaElFormDeberiaMostrarLaVistaHomeConUnMensajeDeError() throws ClaveInvalidaException, CorreoInvalidoException {
        var datosDeEntradaDelLogin = givenQueUnClienteRegistradoIngresaUnCorreoInvalido();
        whenEnviaAValidarElFormularioConLosDatosIngresados(datosDeEntradaDelLogin);
        thenDeberiaMostrarLaVistaDelHomeConUnMensajeDeError("home", "correo_invalido", "Ingresaste un correo invalido, por favor verifica que lo hayas ingresado correctamente");
    }

    private DatosLogin givenQueUnClienteRegistradoIngresaUnCorreoInvalido() {
        return setearDatosDeLogin("lalala", "Aa####04");
    }

    private DatosLogin setearDatosDeLogin(String correo, String clave) {
        return new DatosLogin(correo, clave);
    }

    @Test
    public void dadoQueUnClienteIngresaUnaClaveInvalidaCuandoEnviaAValidarElFormDeberiaMostrarseLaVistaDelHomeConUnMensajeDeError() throws ClaveInvalidaException, CorreoInvalidoException {
        DatosLogin datosLogin = givenQueUnClienteIngresaUnaClaveInvalida();
        whenEnviaAValidarElFormularioConLosDatosIngresados(datosLogin);
        thenDeberiaMostrarLaVistaDelHomeConUnMensajeDeError("home", "clave_invalida", "Ingresaste una calve invalida, verifica que clave contenga: al menos una mayuscula, una o mas minusculas, uno o mas numeros, y ocho carateres de logintud");
    }

    private DatosLogin givenQueUnClienteIngresaUnaClaveInvalida() {
        var datosLogin = new DatosLogin("lala@lala.com", "aa####04");
        return datosLogin;
    }

    private void whenEnviaAValidarElFormularioConLosDatosIngresados(DatosLogin datosDeEntradaDelLogin) {
        this.modelAndView = controladorLogin.validarFormularioDeLoginEnviado(datosDeEntradaDelLogin);
    }

    private void thenDeberiaMostrarLaVistaDelHomeConUnMensajeDeError(String view, String nameError, String value) {
        assertThat(modelAndView.getViewName()).isEqualTo(view);
        assertThat(modelAndView.getModel().get(nameError)).isEqualTo(value);
    }

    @Test
    public void dadoQueUnClienteRegistradoIniciaSesionCuandoLLamoAlServicioParaBuscarELUsuarioPorCorreoDeberiaValidarCorrectamente() throws ClaveInvalidaException {
        var clienteRegistrado = givenQueExitenUnClienteRegistrado();
        whenLLamoAlServicioDeLoginParaBuscarAlUsuarioPorCorreo(clienteRegistrado);
        whenEnviaAValidarElFormularioConLosDatosIngresados(clienteRegistrado);
        thenDeberiaValidarCorrectamenteYRedireccionarALaPaginaPrincipalConUnMensajeDeExito(clienteRegistrado);
    }

    private void whenLLamoAlServicioDeLoginParaBuscarAlUsuarioPorCorreo(DatosLogin clienteRegistrado) {
        when(servicioLogin.buscarClientePorCorreo(clienteRegistrado.getCorreo())).thenReturn(new Cliente());
    }
}
