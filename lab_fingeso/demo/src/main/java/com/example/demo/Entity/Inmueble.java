package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "inmueble")
public class Inmueble {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "id_User")
    private long idUser;

    private String name;

    private String description;

    private long metros2;

    private String type;

    private String direccion;

    private long precio;

    private String fotoUrl;

    public Inmueble() {}

    public Inmueble(long IdUser, String name, String description, long metros2, String type, String direccion, long precio, String fotoUrl) {
        this.idUser = IdUser;
        this.name = name;
        this.description = description;
        this.metros2 = metros2;
        this.type = type;
        this.direccion = direccion;
        this.precio = precio;
        this.fotoUrl = fotoUrl;
    }

    // Getters y setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long id_user) {
        this.idUser = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getMetros2() {
        return metros2;
    }

    public void setMetros2(long metroCuadrados) {
        this.metros2 = metroCuadrados;
    }

    public String getType() {
        return type;
    }

    public void setType(String tipo) {
        this.type = tipo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }
}