package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Servicio {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Empresa empresa;
    private String descripcion;
    private Float valor;

    public Servicio(Long id, Empresa empresa, String descripcion, Float valor) {
        this.id = id;
        this.empresa = empresa;
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public Servicio() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
}
