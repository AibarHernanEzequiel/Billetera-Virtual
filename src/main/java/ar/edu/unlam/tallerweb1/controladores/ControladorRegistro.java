package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

public class ControladorRegistro {

    @RequestMapping(method = RequestMethod.GET, path = "/ir-a-registrarme")
    public ModelAndView irAlFormularioDeRegistro() {
        var viewName = "formulario-registro";
        return setModelAndView(viewName);
    }

    private ModelAndView setModelAndView(String viewName) {
        return new ModelAndView(viewName, setModelMap("datosRegistro", new DatosRegistro()));
    }

    private ModelMap setModelMap(String nombreDelAtributo, Object valorDelAtributo) {
        return new ModelMap(nombreDelAtributo, valorDelAtributo);
    }

}
