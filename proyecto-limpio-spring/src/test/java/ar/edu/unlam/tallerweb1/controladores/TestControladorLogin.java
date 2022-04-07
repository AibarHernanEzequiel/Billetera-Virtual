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
        var clienteRegistrado = givenQueExitenUnClienteRegistrado();
        var datosLogin = whenCompletaElFormDeLoginYLoEnviaAValidar(clienteRegistrado);
        thenDeberiaValidarCorrectamenteYRedireccionarALaPaginaPrincipalConUnMensajeDeExito(datosLogin);
    }

    private DatosLogin whenCompletaElFormDeLoginYLoEnviaAValidar(Cliente clienteRegistrado) {
        var validadorDeCorreo = new ValidadorDeCorreo(clienteRegistrado.getCorreo());
        var validadorDeClave = new ValidadorDeClave(clienteRegistrado.getClave());
        var datosLogin = new DatosLogin(validadorDeCorreo, validadorDeClave);
        this.modelAndView = controladorLogin.validarFormularioDeLoginEnviado(datosLogin);
        return datosLogin;
    }

    private void thenDeberiaValidarCorrectamenteYRedireccionarALaPaginaPrincipalConUnMensajeDeExito(DatosLogin datosLogin) {
        assertThat(datosLogin.esValidoElCorreo()).isTrue();
        assertThat(datosLogin.esValidaClave()).isTrue();
        assertThat(modelAndView.getModelMap().get("registro_exitoso")).isNotNull();
        assertThat(modelAndView.getModelMap().get("registro_exitoso")).isEqualTo("true");
        assertThat(modelAndView.getViewName()).isEqualTo("redirect:/principal");
    }

    private Cliente givenQueExitenUnClienteRegistrado() {
        var clienteRegistrado = new Cliente();
        clienteRegistrado.setCorreo("lala@lala.com");
        clienteRegistrado.setClave("Aa####04");
        return clienteRegistrado;
    }
}
