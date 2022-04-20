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
        // Given que un usuario se encuentra en el home
        givenQueUnUsuarioSeEncuentraEnElHome();
        // When intenta acceder al formulario de registro
        whenIntentaAccederAlFormularioDeRegistro();

        // Then deberia mostrar el formulario de registro
        thenDeberiaMostrarElFormularioRegistro();
    }

    private void givenQueUnUsuarioSeEncuentraEnElHome()
    {
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

    private void whenIntentaAccederAlFormularioDeRegistro()
    {
        modelAndView = controladorRegistro.irAlFormularioRegistro(modelMap);
    }

    @Test
    public void test()
    {

    }
}
