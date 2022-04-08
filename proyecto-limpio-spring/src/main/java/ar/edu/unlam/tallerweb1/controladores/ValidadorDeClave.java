package ar.edu.unlam.tallerweb1.controladores;

public class ValidadorDeClave {

    private Boolean valida;

    public ValidadorDeClave(String clave) {
        validarClave(clave);
    }

    public void validarClave(String clave) {
        valida = true;
    }

    public Boolean getValida() {
        return valida;
    }

}
