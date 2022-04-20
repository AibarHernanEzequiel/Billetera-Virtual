package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorRegistro
{

    @RequestMapping(method = RequestMethod.GET, path = "/ir-a-registrarme")
    public ModelAndView irAlFormularioRegistro(ModelMap modelMap)
    {
        modelMap.put("datosRegistro", new DatosRegistro());
        return new ModelAndView("formulario-registro", modelMap);
    }
}
