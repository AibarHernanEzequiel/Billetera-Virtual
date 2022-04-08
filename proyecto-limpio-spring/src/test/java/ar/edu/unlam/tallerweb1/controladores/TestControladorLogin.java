package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Cliente;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class TestControladorLogin {

    private ControladorLogin controladorLogin;
    private ModelAndView modelAndView;

    @Before
    public void init() {
        controladorLogin = new ControladorLogin();
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
    public void dadoQueUnClienteCompletaElFormDeLoginCuandoLoEnviaDeberiaAccederCorrectamente() {
        var datosLogin = givenQueExitenUnClienteRegistrado();
        whenEnviaAValidarElFormularioConLosDatosIngresados(datosLogin);
        thenDeberiaValidarCorrectamenteYRedireccionarALaPaginaPrincipalConUnMensajeDeExito(datosLogin);
    }


    private DatosLogin givenQueExitenUnClienteRegistrado() {
        var datosDeEntradaLogin = setearDatosDeLogin(new ValidadorDeCorreo("lala@lala.com"), new ValidadorDeClave("Aa####04"));
        return datosDeEntradaLogin;
    }

    private void thenDeberiaValidarCorrectamenteYRedireccionarALaPaginaPrincipalConUnMensajeDeExito(DatosLogin datosLogin) {
        assertThat(datosLogin.getValidadorDeCorreo()).isTrue();
        assertThat(datosLogin.getValidadorDeCorreo()).isTrue();
        assertThat(modelAndView.getModelMap().get("registro_exitoso")).isNotNull();
        assertThat(modelAndView.getModelMap().get("registro_exitoso")).isEqualTo("true");
        assertThat(modelAndView.getViewName()).isEqualTo("redirect:/principal");
    }

    @Test
    public void dadoQueUnclienteRegistradoIngresaUnCorreoInvalidoCuandoEnviaElFormDeberiaMostrarLaVistaHomeConUnMensajeDeError() {
        var datosDeEntradaDelLogin = givenQueUnClienteRegistradoIngresaUnCorreoInvalido();
        whenEnviaAValidarElFormularioConLosDatosIngresados(datosDeEntradaDelLogin);
        thenDeberiaMostrarLaVistaDelHomeConUnMensajeDeError();
    }

    private DatosLogin givenQueUnClienteRegistradoIngresaUnCorreoInvalido() {
        return setearDatosDeLogin(new ValidadorDeCorreo("lalala"), new ValidadorDeClave("Aa####04"));
    }

    private void whenEnviaAValidarElFormularioConLosDatosIngresados(DatosLogin datosDeEntradaDelLogin) {
        this.modelAndView = controladorLogin.validarFormularioDeLoginEnviado(datosDeEntradaDelLogin);
    }

    private void thenDeberiaMostrarLaVistaDelHomeConUnMensajeDeError() {
        assertThat(modelAndView.getViewName()).isEqualTo("home");
        assertThat(modelAndView.getModel().get("correo_invalido")).isEqualTo("Ingresaste un correo invalido, por favor verifica que lo hayas ingresado correctamente");
    }

    private DatosLogin setearDatosDeLogin(ValidadorDeCorreo correo, ValidadorDeClave clave) {
        return new DatosLogin(correo, clave);
    }
}
