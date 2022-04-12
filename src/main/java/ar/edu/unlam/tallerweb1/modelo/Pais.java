package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pais {
    @Id
    @GeneratedValue
    private Long id;
    private String descripcion;

    public Pais(Long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Pais() {

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
}
