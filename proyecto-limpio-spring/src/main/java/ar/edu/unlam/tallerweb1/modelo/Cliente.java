package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Cliente extends Usuario {

    @OneToOne
    private Cuenta cuenta;
    private Date fechaDeNacimiento;

    public Cliente(Long id, String nombre, String apellido, String correo, String dni, String telefono, String nick, String clave, Date fechaDeNacimiento, Cuenta cuenta) {
        super(id, nombre, apellido, correo, dni, telefono, nick, clave);
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.cuenta = cuenta;
    }

    public Cliente(Date fechaDeNacimiento, Cuenta cuenta) {
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.cuenta = cuenta;
    }

    public Cliente() {

    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
}
