package ar.edu.unlam.tallerweb1.controladores;

public class DatosRegistro {

    private String nombre;
    private String apellido;
    private String telefono;
    private String dni;
    private String correo;
    private String nickname;
    private String clave;
    private String repiteClave;

    public DatosRegistro(String nombre, String apellido, String telefono, String dni, String correo, String nickname, String clave, String repiteClave) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.dni = dni;
        this.correo = correo;
        this.nickname = nickname;
        this.clave = clave;
        this.repiteClave = repiteClave;
    }

    public DatosRegistro() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
