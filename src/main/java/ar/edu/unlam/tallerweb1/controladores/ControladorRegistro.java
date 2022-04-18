package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorRegistro {

    @RequestMapping(method = RequestMethod.GET, path = "/ir-a-registrarme")
    public ModelAndView irAlFormularioDeRegistro() {
        return setModelAndView("formulario-registro", setModelMap("datosRegistro", new DatosRegistro()));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/validar-formulario-registro")
    public ModelAndView validarFormularioDeRegistro(DatosRegistro datosRegistro) {
        return null;
    }

    private ModelAndView setModelAndView(String viewName, ModelMap model) {
        return new ModelAndView(viewName, model);
    }

    private ModelMap setModelMap(String nombreDelAtributo, Object valorDelAtributo) {
        return new ModelMap(nombreDelAtributo, valorDelAtributo);
    }
}
