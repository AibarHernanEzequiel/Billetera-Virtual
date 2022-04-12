package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Localidad {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Provincia provincia;
    private String descripcion;

    public Localidad(Long id, Provincia provincia, String descripcion) {
        this.id = id;
        this.provincia = provincia;
        this.descripcion = descripcion;
    }

    public Localidad() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
