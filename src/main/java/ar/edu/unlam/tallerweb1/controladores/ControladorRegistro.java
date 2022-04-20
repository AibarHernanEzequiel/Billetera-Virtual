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
        return getModelAndView("formulario-registro", modelMap);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/validar-formulario-registro")
    public ModelAndView validarFormularioRegistro(DatosRegistro datosRegistro, ModelMap modelMap)
    {
        var viewName = "redirect:/login";
        if (!ValidadorDeCorreo.validarCorreo(datosRegistro.getCorreo()))
        {
            modelMap.put("correo_invalido","Se introdujo un correo invalido, verifique el correo ingresado");
            viewName = "formulario-registro";
        }
        else modelMap.put("registro_exitoso","true");
        return getModelAndView(viewName, modelMap);
    }

    private ModelAndView getModelAndView(String viewName, ModelMap modelMap) {
        return new ModelAndView(viewName, modelMap);
    }
}
