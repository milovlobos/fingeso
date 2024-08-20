package com.example.demo.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="promocionado") // Define que esta clase es una entidad que se mapea a una tabla llamada "promocionado"
public class Promocionado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // El campo id es la clave primaria de la tabla "promocionado" y se genera automáticamente
    private long id;

    @JoinColumn(name = "inmueble_id", referencedColumnName = "id")
    // Define la columna que hace referencia a la clave foránea (foreign key) del inmueble asociado
    private long inmueble_id;

    // Almacena el metodo de pago
    private double precio;

    // Almacena la fecha y hora de la promoción
    private LocalDateTime fecha;


    public Promocionado(long inmueble_id, double precio, LocalDateTime fecha) {

        this.inmueble_id = inmueble_id;
        this.precio = precio;
        this.fecha = fecha;

    }

    public Promocionado() {

    }

    // Métodos getters y setters para acceder y modificar los atributos de Promocionado

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
