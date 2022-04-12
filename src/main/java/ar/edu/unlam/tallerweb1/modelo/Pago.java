package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Pago {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Tarjeta tarjeta;
    private EstadoDepago estadoDepago;
    private LocalDate fechaYHora;
    private Float monto;

    public Pago(Long id, Tarjeta tarjeta, EstadoDepago estadoDepago, LocalDate fechaYHora, Float monto) {
        this.id = id;
        this.tarjeta = tarjeta;
        this.estadoDepago = estadoDepago;
        this.fechaYHora = fechaYHora;
        this.monto = monto;
    }

    public Pago() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    public EstadoDepago getEstadoDepago() {
        return estadoDepago;
    }

    public void setEstadoDepago(EstadoDepago estadoDepago) {
        this.estadoDepago = estadoDepago;
    }

    public LocalDate getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDate fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }
}
