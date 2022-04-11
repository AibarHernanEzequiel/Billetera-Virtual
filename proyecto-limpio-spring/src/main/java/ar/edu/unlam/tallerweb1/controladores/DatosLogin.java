package ar.edu.unlam.tallerweb1.controladores;

public class DatosLogin {

    private String correo;
    private Boolean validadorDeCorreo;
    private Boolean validadorDeClave;

    public DatosLogin(ValidadorDeCorreo validadorDeCorreo, ValidadorDeClave validadorDeClave) {
        this.validadorDeCorreo = validadorDeCorreo.getValida();
        this.validadorDeClave = validadorDeClave.getValida();
        this.correo = validadorDeCorreo.getCorreo();
    }

    public DatosLogin() {
    }

    public Boolean getValidadorDeCorreo() {
        return validadorDeCorreo;
    }

    public Boolean getValidadorDeClave() {
        return validadorDeClave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
