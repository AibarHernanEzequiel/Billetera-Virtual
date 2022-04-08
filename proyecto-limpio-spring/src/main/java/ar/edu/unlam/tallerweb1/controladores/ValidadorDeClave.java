package ar.edu.unlam.tallerweb1.controladores;

public class ValidadorDeClave {

    private Boolean valida;

    public ValidadorDeClave(String clave) {
        validarClave(clave);
    }

    public void validarClave(String clave) {
        valida = contieneMayuscula(clave) && contieneMinuscula(clave);
    }

    private boolean contieneMayuscula(String clave) {
        for (int i = 0; i < clave.length(); i++)
            if (Character.isUpperCase(clave.charAt(i))) {
                return true;
            }
        return false;
    }

    private boolean contieneMinuscula(String clave) {
        for (int i = 0; i < clave.length(); i++)
            if (Character.isLowerCase(clave.charAt(i))) {
                return true;
            }
        return false;
    }

    public Boolean getValida() {
        return valida;
    }

}
