package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.excepciones.ClaveInvalidaException;
import ar.edu.unlam.tallerweb1.excepciones.ClavesNoCoincidentesException;
import ar.edu.unlam.tallerweb1.excepciones.CorreoInvalidoException;
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
        var viewName = getViewName(datosRegistro, modelMap);
        return getModelAndView(viewName, modelMap);
    }

    private String getViewName(DatosRegistro datosRegistro, ModelMap modelMap)
    {
        String viewName = "formulario-registro";
        try
        {
            ValidadorDeCorreo.validarCorreo(datosRegistro.getCorreo());
            ValidadorDeClave.validarClave(datosRegistro.getClave());
            ValidadorDeClave.validarClaveYRepiteClave(datosRegistro.getClave(),datosRegistro.getRepiteClave());
            modelMap.put("registro_exitoso","true");
            viewName = "redirect:/login";
        } catch (CorreoInvalidoException e) {
            modelMap.put("correo_invalido", MensajeDeError.correoInvalido());
        } catch (ClaveInvalidaException e) {
            modelMap.put("clave_invalida", MensajeDeError.claveInvalida());
        } catch (ClavesNoCoincidentesException e) {
            modelMap.put("claves_incorrectas", MensajeDeError.clavesIncorrectas());
        }
        return viewName;
    }

    private ModelAndView getModelAndView(String viewName, ModelMap modelMap)
    {
        return new ModelAndView(viewName, modelMap);
    }
}
