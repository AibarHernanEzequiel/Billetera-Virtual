package ar.edu.unlam.tallerweb1.controladores;

public class DatosLogin {

    private Boolean validadorDeCorreo;
    private Boolean validadorDeClave;

    public DatosLogin(ValidadorDeCorreo validadorDeCorreo, ValidadorDeClave validadorDeClave) {
        this.validadorDeCorreo = validadorDeCorreo.getValida();
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

}
