package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Administrador extends Usuario {

    private Date fechaDeingreso;

    public Administrador(Long id, String nombre, String apellido, String correo, String dni, String telefono, String nick, String clave, Date fechaDeingreso) {
        super(id, nombre, apellido, correo, dni, telefono, nick, clave);
        this.fechaDeingreso = fechaDeingreso;
    }

    public Administrador(Date fechaDeingreso) {
        this.fechaDeingreso = fechaDeingreso;
    }

    public Administrador() {

    }

    public Date getFechaDeingreso() {
        return fechaDeingreso;
    }

    public void setFechaDeingreso(Date fechaDeingreso) {
        this.fechaDeingreso = fechaDeingreso;
    }
}
