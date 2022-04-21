package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.excepciones.ClaveInvalidaException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.doThrow;

public class TestControladorRegistro
{

    private ControladorRegistro controladorRegistro;
    private ModelAndView modelAndView;
    private ModelMap modelMap;

    @Before
    public void init()
    {
        this.controladorRegistro = new ControladorRegistro();
        this.modelAndView = new ModelAndView();
        this.modelMap = new ModelMap();
    }

    @Test
    public void queUnClientePuedaAccederAlFormularioDeRegistro()
    {
        givenQueUnUsuarioSeEncuentraEnElHome();
        whenIntentaAccederAlFormularioDeRegistro();
        thenDeberiaMostrarElFormularioRegistro();
    }

    private void givenQueUnUsuarioSeEncuentraEnElHome()
    {
    }

    private void whenIntentaAccederAlFormularioDeRegistro()
    {
        modelAndView = controladorRegistro.irAlFormularioRegistro(modelMap);
    }

    private void thenDeberiaMostrarElFormularioRegistro()
    {
        assertThat(modelAndView.getViewName()).isEqualTo("formulario-registro");
        verificarQueSeEnvieComoModelAttributeUnObjetoDatosRegistro();
    }

    private void verificarQueSeEnvieComoModelAttributeUnObjetoDatosRegistro()
    {
        assertThat(modelAndView.getModel().get("datosRegistro")).isNotNull();
        assertThat(modelAndView.getModel().get("datosRegistro")).isInstanceOf(DatosRegistro.class);
    }

    @Test
    public void queUnClienteAlEnviarElFormularioDeRegistroConSusDatosValideCorrectamente()
    {
        var datosRegistro = givenQueUnClienteIngresaSusDatosEnElFormularioDeRegistro();
        whenElClienteEnviaElFormulario(datosRegistro, modelMap);
        thenDeberiaValidarCorrectamenteMostrandoUnMensajeExitoYRedireccionarAlLogin();
    }

    private DatosRegistro givenQueUnClienteIngresaSusDatosEnElFormularioDeRegistro()
    {
        return new DatosRegistro("Ezequiel","Aibar","ezequielaibar@gmail.com","Ezequiel1","1234","ezequielaibar");
    }

    private void whenElClienteEnviaElFormulario(DatosRegistro datosRegistro, ModelMap modelMap)
    {
        modelAndView = controladorRegistro.validarFormularioRegistro(datosRegistro,modelMap);
    }

    private void thenDeberiaValidarCorrectamenteMostrandoUnMensajeExitoYRedireccionarAlLogin()
    {
        assertThat(modelAndView.getModel().get("registro_exitoso")).isEqualTo("true");
        assertThat(modelAndView.getViewName()).isEqualTo("redirect:/login");
    }

    @Test
    public void queUnClienteAlEnviarElFormConUnCorreoInvalidoMuestreUnMensajeDeError()
    {
        DatosRegistro datosRegistroConCorreoInvalido = givenQueUnClienteIngresaUnCorreoInvalido();
        whenElClienteEnviaElFormulario(datosRegistroConCorreoInvalido,modelMap);
        thenDeberiaMostrarUnMensajeDeErrorEnLaVistaDelFormulario("correo_invalido", "Se introdujo un correo invalido, verifique el correo ingresado");
    }

    private DatosRegistro givenQueUnClienteIngresaUnCorreoInvalido()
    {
        return new DatosRegistro("Ezequiel","Aibar","ezequielaibar.com","1234","1234","ezequielaibar");
    }

    private void thenDeberiaMostrarUnMensajeDeErrorEnLaVistaDelFormulario(String error, String mensaje)
    {
        assertThat(modelAndView.getModel().get(error)).isEqualTo(mensaje);
        assertThat(modelAndView.getViewName()).isEqualTo("formulario-registro");
    }

    @Test
    public void queUnClienteAlEnviarElFormConUnaClaveInvalidaDeberiaMostrarUnMensajeDeError()
    {
        var datosRegistroConClaveInvalida = givenQueUnClienteIngresaUnaClaveInvalida();
        whenElClienteEnviaElFormulario(datosRegistroConClaveInvalida, modelMap);
        thenDeberiaMostrarUnMensajeDeErrorEnLaVistaDelFormulario("clave_invalida","Ingresaste un clave invalida, para que sea valida debe contener 10 caracteres, entre ellos al menos una mayuscula, y debe contener al menos dos numeros");
    }

    private DatosRegistro givenQueUnClienteIngresaUnaClaveInvalida()
    {
        return new DatosRegistro("Ezequiel","Aibar","ezequielaibar@gmail.com","1234","1234","ezequielaibar");
    }
}
