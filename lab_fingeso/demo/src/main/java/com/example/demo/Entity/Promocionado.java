package com.example.demo.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="promocionado")
public class Promocionado {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name = "inmueble_id", referencedColumnName = "id")
    private long inmueble_id;

    private double precio;

    private LocalDateTime fecha;



    public Promocionado( long inmueble_id, double precio, LocalDateTime fecha) {
        this.id=id;
        this.inmueble_id = inmueble_id;
        this.precio = precio;
        this.fecha=fecha;
    }





    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getInmueble_id() {
        return inmueble_id;
    }

    public void setInmueble_id(long inmueble_id) {
        this.inmueble_id = inmueble_id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
