package ar.edu.unlam.tallerweb1.controladores;

public class DatosLogin {

    private String correo;
    private String clave;
    private Boolean validadorDeCorreo;
    private Boolean validadorDeClave;

    public DatosLogin(String correo, String clave) {
        this.correo = correo;
        this.clave = clave;
        ValidadorDeCorreo validadorDeCorreo = new ValidadorDeCorreo(correo);
        this.validadorDeCorreo = validadorDeCorreo.getValida();
        ValidadorDeClave validadorDeClave = new ValidadorDeClave(clave);
        this.validadorDeClave = validadorDeClave.getValida();
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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
