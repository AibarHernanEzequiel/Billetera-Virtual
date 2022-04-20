package ar.edu.unlam.tallerweb1.controladores;

public class DatosRegistro
{
    private String nombre;
    private String apellido;
    private String correo;
    private String clave;
    private String repiteClave;

    public DatosRegistro(String nombre, String apellido, String correo, String clave, String repiteClave)
    {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.clave = clave;
        this.repiteClave = repiteClave;
    }

    public DatosRegistro()
    {
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setApellido(String apellido)
    {
        this.apellido = apellido;
    }

    public void setCorreo(String correo)
    {
        this.correo = correo;
    }

    public void setClave(String clave)
    {
        this.clave = clave;
    }

    public void repiteClave(String repiteClave)
    {
        this.repiteClave = repiteClave;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getApellido()
    {
        return apellido;
    }

    public String getCorreo()
    {
        return correo;
    }

    public String getClave()
    {
        return clave;
    }

    public String getRepiteClave()
    {
        return repiteClave;
    }
}
