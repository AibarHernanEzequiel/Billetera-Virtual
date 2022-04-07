package ar.edu.unlam.tallerweb1.controladores;

public class DatosLogin {

    private String correo;
    private String clave;
    private Boolean validadorDeCorreo;
    private Boolean validadorDeClave;

    public DatosLogin(ValidadorDeCorreo validadorDeCorreo, ValidadorDeClave validadorDeClave) {
        this.validadorDeCorreo = validadorDeCorreo.validarCorreo(correo);
        this.validadorDeClave = validadorDeClave.validarClave(clave);
    }

    public DatosLogin() {
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Boolean esValidoElCorreo() {
        return validadorDeCorreo;
    }

    public void setValidadorDeCorreo(Boolean validadorDeCorreo) {
        this.validadorDeCorreo = validadorDeCorreo;
    }

    public Boolean esValidaClave() {
        return validadorDeClave;
    }

    public void setValidadorDeClave(Boolean validadorDeClave) {
        this.validadorDeClave = validadorDeClave;
    }
}
