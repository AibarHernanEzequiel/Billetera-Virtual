package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;
    @ManyToOne
    private Cliente emisor;
    @OneToOne
    private Cliente receptor;
    private Float monto;
    private LocalDate fechaYHora;

    public Transferencia(Long id, Long codigo, Cliente emisor, Cliente receptor, Float monto, LocalDate fechaYHora) {
        this.id = id;
        this.codigo = codigo;
        this.emisor = emisor;
        this.receptor = receptor;
        this.monto = monto;
        this.fechaYHora = fechaYHora;
    }

    public Transferencia() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Cliente getEmisor() {
        return emisor;
    }

    public void setEmisor(Cliente emisor) {
        this.emisor = emisor;
    }

    public Cliente getReceptor() {
        return receptor;
    }

    public void setReceptor(Cliente receptor) {
        this.receptor = receptor;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public LocalDate getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDate fechaYHora) {
        this.fechaYHora = fechaYHora;
    }
}
