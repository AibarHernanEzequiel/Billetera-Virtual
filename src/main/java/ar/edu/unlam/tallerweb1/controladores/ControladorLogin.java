package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.excepciones.ClaveInvalidaException;
import ar.edu.unlam.tallerweb1.excepciones.ClienteInexistenteException;
import ar.edu.unlam.tallerweb1.excepciones.CorreoInvalidoException;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorLogin {

    private final ServicioCliente servicioLogin;

    @Autowired
    public ControladorLogin(ServicioCliente servicioLogin) {
        this.servicioLogin = servicioLogin;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/home")
    public ModelAndView irALaHomePage() {
        var viewName = "home";
        var modelMap = getModelMap();
        modelMap.put("datosLogin", new DatosLogin());
        return getModelAndView(viewName, modelMap);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/validar-formulario-login")
    public ModelAndView validarFormularioDeLoginEnviado(DatosLogin datosLogin) {
        var modelMap = getModelMap();
        var viewName = obtenerVistaDependiendoSiSonValidasLaClaveYElCorreoIngresados(datosLogin, modelMap);
        return getModelAndView(viewName, modelMap);
    }

    private String obtenerVistaDependiendoSiSonValidasLaClaveYElCorreoIngresados(DatosLogin datosLogin, ModelMap modelMap) {
        var viewName = "home";
        try {
            validarClaveYCorreo(datosLogin);
            servicioLogin.buscarClientePorCorreo(datosLogin.getCorreo());
            viewName = "redirect:/principal";
            modelMap.put("registro_exitoso", "true");
        } catch (ClaveInvalidaException e) {
            modelMap.put("clave_invalida", "Ingresaste una calve invalida, verifica que clave contenga: al menos una mayuscula, una o mas minusculas, uno o mas numeros, y ocho carateres de logintud");
        } catch (CorreoInvalidoException e) {
            modelMap.put("correo_invalido", "Ingresaste un correo invalido, por favor verifica que lo hayas ingresado correctamente");
        } catch (ClienteInexistenteException e) {
            e.printStackTrace();
        }
        return viewName;
    }

    private void validarClaveYCorreo(DatosLogin datosLogin) throws ClaveInvalidaException, CorreoInvalidoException {
        if (!datosLogin.getValidadorDeClave()) throw new ClaveInvalidaException();
        if (!datosLogin.getValidadorDeCorreo()) throw new CorreoInvalidoException();
    }


    private ModelMap getModelMap() {
        return new ModelMap();
    }

    private ModelAndView getModelAndView(String viewName, ModelMap modelMap) {
        return new ModelAndView(viewName, modelMap);
    }
}
