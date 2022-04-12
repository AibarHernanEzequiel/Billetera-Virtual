package ar.edu.unlam.tallerweb1.controladores;

public class ValidadorDeCorreo {

    private Boolean valida;
    private String correo;

    public ValidadorDeCorreo(String correo) {
        this.correo = correo;
        validarCorreo(this.correo);
    }

    public void validarCorreo(String correo) {
        valida = correo.contains("@") && correo.endsWith(".com");
    }

    public Boolean getValida() {
        return valida;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
