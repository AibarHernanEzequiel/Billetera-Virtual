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

    @Test
    public void test() {
        /*
         * dado que un cliente ingresa los datos de registro en el formulario de registro
         * cuando el cliente envia el formulario de registro a validar
         * deberia validar correctamente
         * */
        var datosRegistro = new DatosRegistro("nombre", "apellido", "telefono", "dni", "correo", "nickname", "clave", "repiteClave");
        this.modelAndView = controladorRegistro.validarFormularioDeRegistro(datosRegistro);
        assertThat(modelAndView.getViewName()).isEqualTo("redirect:/home");
        assertThat(modelAndView.getModelMap().get("registro_exitoso")).isEqualTo("true");
        
    }
}
