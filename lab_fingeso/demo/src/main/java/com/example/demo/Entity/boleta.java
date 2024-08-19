package com.example.demo.Entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "boleta")
public class boleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private Long idUser;

    @Column(name = "inmueble_id")
    private Long idInmueble;

    private long precio;
    private Date fecha;
    private String metodoPago;
    private String descripcion;

    public boleta(long id_user, long precio, Date date, String metodoPago, String descripcion) {
        this.idUser = id_user;
        this.precio = precio;
        this.fecha = date;
        this.metodoPago = metodoPago;
        this.descripcion = descripcion;

    }

    public boleta(long id_usuario, long id_inmueble, long precio, Date fecha, String metodoPago, String descripcion) {
        this.idUser = id_usuario;
        this.idInmueble = id_inmueble;
        this.precio = precio;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
        this.descripcion = descripcion;

    }

    // Getters y setters

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_user() {
        return idUser;
    }

    public void setId_user(long id_user) {
        this.idUser = id_user;
    }

    public long getId_inmueble() {
        return idInmueble;
    }

    public void setId_inmueble(long id_inmueble) {
        this.idInmueble = id_inmueble;
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