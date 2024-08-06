package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="promocionado")
public class Promocionado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "inmueble_id", referencedColumnName = "id")
    private Inmueble inmueble;

    private double precio;

    public Promocionado() {}

    public Promocionado(Inmueble inmueble, double precio) {
        this.inmueble = inmueble;
        this.precio = precio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
