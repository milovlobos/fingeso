package com.example.demo.Entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "boleta")
public class boleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "inmueble_id")
    private Inmueble inmueble;

    private long precio;
    private Date fecha;
    private String metodoPago;

    public boleta() {}

    public boleta(Usuario usuario, Inmueble inmueble, long precio, Date fecha, String metodoPago) {
        this.usuario = usuario;
        this.inmueble = inmueble;
        this.precio = precio;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
    }

    // Getters y setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
}