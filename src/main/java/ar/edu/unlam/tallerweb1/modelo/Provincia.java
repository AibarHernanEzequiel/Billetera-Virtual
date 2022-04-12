package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Provincia {
    @Id
    @GeneratedValue
    private Long id;
    private String descripcion;
    @ManyToOne
    private Pais pais;

    public Provincia(Long id, String descripcion, Pais pais) {
        this.id = id;
        this.descripcion = descripcion;
        this.pais = pais;
    }

    public Provincia() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}
