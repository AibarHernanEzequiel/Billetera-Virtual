package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Banco banco;
    @ManyToOne
    private Cliente cliente;
    private String numero;
    private String fechaDeInicio;
    private String fechaDeFin;
    private String nombreDeFrente;
    private TipoTarjeta tipoTarjeta;
    private Compania compania;

    public Tarjeta(Long id, Banco banco, Cliente cliente, String numero, String fechaDeInicio, String fechaDeFin, String nombreDeFrente, TipoTarjeta tipoTarjeta, Compania compania) {
        this.id = id;
        this.banco = banco;
        this.cliente = cliente;
        this.numero = numero;
        this.fechaDeInicio = fechaDeInicio;
        this.fechaDeFin = fechaDeFin;
        this.nombreDeFrente = nombreDeFrente;
        this.tipoTarjeta = tipoTarjeta;
        this.compania = compania;
    }

    public Tarjeta() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFechaDeInicio() {
        return fechaDeInicio;
    }

    public void setFechaDeInicio(String fechaDeInicio) {
        this.fechaDeInicio = fechaDeInicio;
    }

    public String getFechaDeFin() {
        return fechaDeFin;
    }

    public void setFechaDeFin(String fechaDeFin) {
        this.fechaDeFin = fechaDeFin;
    }

    public String getNombreDeFrente() {
        return nombreDeFrente;
    }

    public void setNombreDeFrente(String nombreDeFrente) {
        this.nombreDeFrente = nombreDeFrente;
    }

    public TipoTarjeta getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(TipoTarjeta tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public Compania getCompania() {
        return compania;
    }

    public void setCompania(Compania compania) {
        this.compania = compania;
    }
}
