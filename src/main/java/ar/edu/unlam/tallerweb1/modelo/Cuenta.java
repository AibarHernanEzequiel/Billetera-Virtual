package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Moneda moneda;
    private Float saldo;
    private LocalDate fechaDeCreacion;

    public Cuenta(Long id, Float saldo, LocalDate fechaDeCreacion, Moneda moneda) {
        this.id = id;
        this.saldo = saldo;
        this.fechaDeCreacion = fechaDeCreacion;
        this.moneda = moneda;
    }

    public Cuenta() {
        this.saldo = 0.0f;
        this.fechaDeCreacion = LocalDate.now();
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    public LocalDate getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDate fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }
}
