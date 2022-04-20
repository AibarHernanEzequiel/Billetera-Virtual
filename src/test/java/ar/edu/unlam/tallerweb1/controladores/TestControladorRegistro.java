package ar.edu.unlam.tallerweb1.controladores;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Java6Assertions.assertThat;

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
        return new DatosRegistro("Ezequiel","Aibar","ezequielaibar@gmail.com","1234","1234");
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
        thenDeberiaMostrarUnMensajeDeErrorEnLaVistaDelFormulario();
    }

    private DatosRegistro givenQueUnClienteIngresaUnCorreoInvalido()
    {
        return new DatosRegistro("Ezequiel","Aibar","ezequielaibar.com","1234","1234");
    }

    private void thenDeberiaMostrarUnMensajeDeErrorEnLaVistaDelFormulario()
    {
        assertThat(modelAndView.getModel().get("correo_invalido")).isEqualTo("Se introdujo un correo invalido, verifique el correo ingresado");
        assertThat(modelAndView.getViewName()).isEqualTo("formulario-registro");
    }
}
