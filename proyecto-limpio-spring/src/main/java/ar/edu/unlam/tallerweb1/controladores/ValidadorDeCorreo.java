package ar.edu.unlam.tallerweb1.controladores;

public class ValidadorDeCorreo {

    private Boolean valida;

    public ValidadorDeCorreo(String correo) {
        validarCorreo(correo);
    }

    public void validarCorreo(String correo) {
        valida = correo.contains("@") && correo.endsWith(".com");
    }

    public Boolean getValida() {
        return valida;
    }
}
