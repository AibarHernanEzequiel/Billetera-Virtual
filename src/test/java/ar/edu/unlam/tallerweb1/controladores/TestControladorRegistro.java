package ar.edu.unlam.tallerweb1.controladores;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class TestControladorRegistro {

    private ControladorRegistro controladorRegistro;
    private ModelAndView modelAndView;

    @Before
    public void init() {
        controladorRegistro = new ControladorRegistro();
        modelAndView = new ModelAndView();
    }

    @Test
    public void dadoQueUnClienteQuiereRegistrarseCuandoIngresaALaSeccionDeRegistroDeberiaMostrarElFormularioDeRegistro() {
        givenQueUnClienteQuiereRegistrarse();
        whenIngresaALaSeccionDeRegistro();
        thenDeberiaMostrarUnaVistaConUnFormularioRegistro();
    }

    private void givenQueUnClienteQuiereRegistrarse() {

    }

    private void whenIngresaALaSeccionDeRegistro() {
        this.modelAndView = controladorRegistro.irAlFormularioDeRegistro();
    }

    private void thenDeberiaMostrarUnaVistaConUnFormularioRegistro() {
        assertThat(modelAndView.getViewName()).isEqualTo("formulario-registro");
        assertThat(modelAndView.getModelMap().get("datosRegistro")).isNotNull();
        assertThat(modelAndView.getModelMap().get("datosRegistro")).isInstanceOf(DatosRegistro.class);
    }
}
